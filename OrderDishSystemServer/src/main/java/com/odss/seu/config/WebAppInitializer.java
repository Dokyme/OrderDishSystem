package com.odss.seu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //会同时创建dispatcherServlet和contextLoaderListener
    //dispatcherServlet加载web组件的bean，而contextLoaderListener加载其他如中间层和数据层组件的bean

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //返回的配置类将会用来配置contextLoaderListener
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //返回的配置类将会配置dispatcherServlet应用上下文中的Bean
        return new Class<?>[]{WebConfig.class};
    }
}
