package com.forum.config;

import com.forum.interceptors.DynamicContentLoader;
import com.forum.interceptors.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final DynamicContentLoader dynamicContentLoader;

    private final LoggerInterceptor loggerInterceptor;

    public WebMvcConfiguration(DynamicContentLoader dynamicContentLoader, LocaleChangeInterceptor localeChangeInterceptor, LoggerInterceptor loggerInterceptor) {
        this.dynamicContentLoader = dynamicContentLoader;
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.dynamicContentLoader);
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/posts/create", "/categories/create");
    }
}
