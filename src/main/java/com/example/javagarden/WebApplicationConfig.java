package com.example.javagarden;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

    // Create spring-managed object to allow the app to access our filter
    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter();
    }

    // Register the filter with the Spring container
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( authenticationFilter() );
        registry.addInterceptor( authorizationFilter() );
    }


    // Register the static resources location with Spring Boot
//    // below is new stuff trying to get css past filter
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//    }

}