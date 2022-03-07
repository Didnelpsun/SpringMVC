// WebConfig.java
package org.didnelpsun.config;

import org.didnelpsun.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Properties;

// 替代SpringMVC.xml
@Configuration
// 扫描组件
@ComponentScan("org.didnelpsun.controller")
// 开启SpringMVC的注解驱动
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    // 配置视图解析器
    // ViewResolverRegistry为视图解析器注册器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/",".jsp");
    }
//    @Bean
//    // 使用内置视图解析器
//    protected InternalResourceViewResolver configureViewResolvers() {
//        // 第一个是前缀，第二个是后缀
//        return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
//    }
    // 配置默认Servlet处理，开启静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 开启DefaultServlet使用
        configurer.enable();
    }
    // 添加view-controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
    // 添加拦截器
    // InterceptorRegistry为拦截器注册器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有路径进行拦截，并对主页面排除
        registry.addInterceptor(new PageInterceptor()).addPathPatterns("/**").excludePathPatterns("/");
    }
    // 添加文件上传解析器
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
    // 添加异常处理器
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        // 设置异常映射
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException", "error");
        exceptionResolver.setExceptionMappings(properties);
        // 获取异常返回的数据
        exceptionResolver.setExceptionAttribute("exception");
        resolvers.add(exceptionResolver);
    }
}