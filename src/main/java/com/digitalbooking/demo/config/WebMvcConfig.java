package com.digitalbooking.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://ec2-3-15-29-50.us-east-2.compute.amazonaws.com/",
                    "http://localhost:3000",
                    "http://localhost:80",
                    "http://doom-staging-20220617.s3-website.us-east-2.amazonaws.com",
                    "http://ec2-18-191-92-48.us-east-2.compute.amazonaws.com"
                    )
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(-1);
    }
}
