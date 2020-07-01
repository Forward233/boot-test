package com.boot.demo.spring.my_import.config;

import com.boot.demo.spring.my_import.service.impl.HelloServiceAImpl;
import com.boot.demo.spring.my_import.service.impl.HelloServiceBImpl;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 15:08
 * @Description:
 */
public class HelloImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloServiceAImpl.class.getName(), HelloServiceBImpl.class.getName()};
    }
}
