package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.backend.Account.controller",
        "com.example.backend.Users.controller",
        "com.example.backend.Habit.controller",
        "com.example.backend.HabitCommunity.controller",
        "com.example.backend.PostComment.controller",
        "com.example.backend.PostCommunity.controller",
        "com.example.backend.PostLikes.controller",
        "com.example.backend.Transaction.controller",
        "com.example.backend.oauth2.controller"
})
public class WebConfig implements WebMvcConfigurer {

    public WebConfig(){
        System.out.println("WebConfig created");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}

