package com.joslin.youtubedataapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer{
	
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*");
		}
		
		
		  @Override
		    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		        registry.addResourceHandler("/**").addResourceLocations(
		                "classpath:/static/");
		        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
		                "classpath:/META-INF/resources/");
		        registry.addResourceHandler("/webjars/**").addResourceLocations(
		                "classpath:/META-INF/resources/webjars/");
		       
		    }

}