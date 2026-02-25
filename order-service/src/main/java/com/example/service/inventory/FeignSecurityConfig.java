package com.example.service.inventory;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignSecurityConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            var attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes!=null) {
               var token = attributes.getRequest().getHeader("Authorization");
               if(token != null){
                   requestTemplate.header("Authorization", token);
               }
            }
        };
    }

}
