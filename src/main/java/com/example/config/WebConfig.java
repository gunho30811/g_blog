package com.example.config;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new JavaTimeModule()); // JavaTimeModule 추가

	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    converter.setObjectMapper(objectMapper); // 수정된 ObjectMapper 설정
	    return converter;
	}

	
    
    // Vue SPA 경로 처리
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/dist/index.html");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dist/**") // 정적 파일 요청 경로
                .addResourceLocations("classpath:/dist/") // 실제 파일 경로
                .setCachePeriod(3600); // 캐시 설정

        // 추가 리소스 핸들러가 필요하다면 추가
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
    
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true); // 강제적으로 UTF-8 인코딩 설정
        return filter;
    }
    
}
