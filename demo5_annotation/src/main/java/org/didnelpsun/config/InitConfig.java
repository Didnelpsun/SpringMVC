// InitConfig.java
package org.didnelpsun.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

// Web工程的初始化类，用来代替web.xml
public class InitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    // 获取根配置，即Spring的配置类，在SSM整合时需要使用
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }
    @Override
    // 获取SpringMVC的配置类
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }
    // 获取Servlet的映射路径，即web.xml中的DispatcherServlet的映射配置。
    // url-pattern由于是根路径，所以是"/"
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    // 定义过滤器
    @Override
    protected Filter[] getServletFilters() {
        // 字符编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        // 设置字符编码
        characterEncodingFilter.setEncoding("UTF-8");
        // 设置强转响应与请求编码
        characterEncodingFilter.setForceRequestEncoding(true);
        characterEncodingFilter.setForceResponseEncoding(true);
        // 请求类型转换过滤器
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter};
    }
}