package com.boot.demo.spring.inject_bean2.register;

import com.alibaba.fastjson.JSON;
import com.boot.demo.spring.inject_bean2.annotation.Proxy;
import com.boot.demo.spring.inject_bean2.factory.ProxyFactoryBean;
import com.boot.demo.spring.inject_bean2.util.PackageUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:20
 * @Description:
 */
@Slf4j
@Component
public class CustomizeBeanRegister implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
    public static final String BASE_PACKAGES = "com.boot.demo.spring.inject_bean2.service";

    /**
     * 注册自定义 bean 到 Spring 容器中
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        List<Class<?>> classNames = Lists.newArrayList();
        PackageUtils.classNames(BASE_PACKAGES, classNames);
        log.info("CustomizeScannerConfigurer 执行动态代理生成类注入ioc容器，生成类列表：{}", JSON.toJSONString(classNames));
        for (Class<?> className : classNames) {
            Proxy proxy = className.getAnnotation(Proxy.class);
            String beanName = proxy.value();
            Class<?> interceptor = proxy.interceptor();

            if ("".equals(beanName)) {
                String shortClassName = ClassUtils.getShortName(className.getName());
                beanName = Introspector.decapitalize(shortClassName);
            }

            // 被代理对象, 即： 实际对象
            Object actualBean = getBean(className);

            // 为被代理对象中依赖的属性注入值
            Field[] actualBeanFields = actualBean.getClass().getDeclaredFields();
            for (Field actualBeanField : actualBeanFields) {
                if (actualBeanField.getModifiers() == Modifier.PRIVATE) {
                    actualBeanField.setAccessible(true);
                    try {
                        actualBeanField.set(actualBean, getBean(actualBeanField.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            // 代理对象操作的拦截器
            Object interceptorBean = null;
            try {
//                actualBean = className.newInstance();
                interceptorBean = interceptor.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(className);
            GenericBeanDefinition definition = (GenericBeanDefinition) beanDefinitionBuilder.getRawBeanDefinition();
            definition.setBeanClass(ProxyFactoryBean.class);
            // 设置属性值
            definition.getPropertyValues().add("target", actualBean);
            definition.getPropertyValues().add("interfaces", className.getInterfaces());
            definition.getPropertyValues().add("interceptor", interceptorBean);
            // 设置可通过 @Autowired 注解访问
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            // 注册到 BeanDefinitionRegistry
            registry.registerBeanDefinition(beanName, definition);
        }
    }

    /**
     * 对bean定义做一些改变
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        String[] beanDefinitionNames = factory.getBeanDefinitionNames();

        // 打印持有的bean的属性情况
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition bd = factory.getBeanDefinition(beanDefinitionName);

            // TODO 可以对 bean 的定义做一些改变

            if (bd.getPropertyValues().size() > 0) {
                log.info("bean[{}] bean properties[{}]", beanDefinitionName, bd.getPropertyValues().size());
            }
        }
    }

    /**
     * 注入普通 bean 到 Spring 容器
     */
    private void ordinaryBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        List<Class<?>> classNames = Lists.newArrayList();
        PackageUtils.classNames(BASE_PACKAGES, classNames);

        for (Class<?> className : classNames) {
            // 创建一个bean的定义类的对象
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(className);

            // 将 Bean 的定义注册到 Spring 容器中
            registry.registerBeanDefinition(className.getSimpleName(), rootBeanDefinition);
        }
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}