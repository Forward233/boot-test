package com.boot.demo.spring.inject_bean.register;

import com.alibaba.fastjson.JSON;
import com.boot.demo.spring.inject_bean.annotation.Proxy;
import com.boot.demo.spring.inject_bean.factory.ServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 12:15
 * @Description:
 */
@Component
@Slf4j
public class ServiceBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor,
        ResourceLoaderAware, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //这里一般我们是通过反射获取需要代理的接口的clazz列表
        //比如判断包下面的类，或者通过某注解标注的类等等
        Set<Class<?>> beanClazzs = scannerPackages("com.boot.demo.spring.inject_bean.service");
        log.info("ServiceBeanDefinitionRegistry 执行动态代理生成类注入ioc容器，生成类列表：{}", JSON.toJSONString(beanClazzs));
        for (Class<?> beanClazz : beanClazzs) {
            Proxy annotation = beanClazz.getAnnotation(Proxy.class);
            if (annotation == null) {
                continue;
            }
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();

            //在这里，我们可以给该对象的属性注入对应的实例。
            //比如mybatis，就在这里注入了dataSource和sqlSessionFactory，
            // 注意，如果采用definition.getPropertyValues()方式的话，
            // 类似definition.getPropertyValues().add("interfaceType", beanClazz);
            // 则要求在FactoryBean（本应用中即ServiceFactory）提供setter方法，否则会注入失败
            // 如果采用definition.getConstructorArgumentValues()，
            // 则FactoryBean中需要提供包含该属性的构造方法，否则会注入失败
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClazz);

            // 为代理bean注入属性
            Object actualBean = applicationContext.getBean(beanClazz);
            Field[] declaredFields = actualBean.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Object bean = null;
                try {
                    bean = applicationContext.getBean(declaredField.getType());
                } catch (BeansException e) {
                    // 排除log类
                    log.error("ioc 容器中未找到此bean...");
                    continue;
                }
                if (declaredField.getModifiers() == Modifier.PRIVATE) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(actualBean, bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        declaredField.set(actualBean, bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 注意，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。
            // FactoryBean是一种特殊的Bean，其返回的对象不是指定类的一个实例，
            // 其返回的是该工厂Bean的getObject方法所返回的对象。
            // 绑定Bean与FactoryBean的关联，在bean实例化时改造增强bean
            definition.setBeanClass(ServiceFactory.class);

            // 此处设置的属性可以FactoryBean实现类中获取
            definition.getPropertyValues().add("target", applicationContext.getBean(beanClazz));
            definition.getPropertyValues().add("interfaces", beanClazz.getInterfaces());

            //这里采用的是byType方式注入，类似的还有byName等
            Class<?>[] interfaces = beanClazz.getInterfaces();
            definition.setAutowireMode(AUTOWIRE_BY_TYPE);
            // 默认取第一个实现的接口
            String beanName = Introspector.decapitalize(interfaces[0].getSimpleName());
            registry.registerBeanDefinition(beanName, definition);
        }
    }

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private MetadataReaderFactory metadataReaderFactory;

    /**
     * 根据包路径获取包及子包下的所有类
     * @param basePackage basePackage
     * @return Set<Class<?>> Set<Class<?>>
     */
    private Set<Class<?>> scannerPackages(String basePackage) {
        Set<Class<?>> set = new LinkedHashSet<>();
//        classpath 和 classpath* 区别：
//        classpath：只会到你的class路径中查找找文件;
//        classpath*：不仅包含class路径，还包括jar文件中(class路径)进行查找.
//        classpath:--->/WEB-INF/classes/           【当前项目】
//        classpath:/--->/WEB-INF/classes/          【当前项目】
//        classpath*:--->/WEB-INF/classes/          【当前项目和该项目引用的所有jar包中的classes路径】
//        classpath*:/--->/WEB-INF/classes/         【当前项目和该项目引用的所有jar包中的classes路径】

        //classpath*:com/boot/demo/base/proxy/inject_bean/service/**/*.class
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                resolveBasePackage(basePackage) + '/' + DEFAULT_RESOURCE_PATTERN;
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    Class<?> clazz;
                    try {
                        clazz = Class.forName(className);
                        if (!clazz.isInterface()) {
                            set.add(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    protected String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(this.getEnvironment().resolveRequiredPlaceholders(basePackage));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private ResourcePatternResolver resourcePatternResolver;

    private ApplicationContext applicationContext;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }

}
