package com.forum.config;

import com.forum.interceptors.DynamicContentLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final DynamicContentLoader dynamicContentLoader;

    public WebMvcConfiguration(DynamicContentLoader dynamicContentLoader, LocaleChangeInterceptor localeChangeInterceptor) {
        this.dynamicContentLoader = dynamicContentLoader;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.dynamicContentLoader);
    }
}
