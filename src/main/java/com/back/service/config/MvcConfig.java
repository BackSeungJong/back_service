package com.back.service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@PropertySource({"classpath:local/config.properties"})
public class MvcConfig implements WebMvcConfigurer {

    public final Environment environment;

    // charactorEncodingFilter를 Bean으로 등록하는 메소드
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return characterEncodingFilter;
    }

    /**
     * Static Resource 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String noticeRoot = environment.getProperty("system.notice.file.root");
        if(noticeRoot.contains("C:/")){
            registry.addResourceHandler("/notice-image/**")
                    .addResourceLocations("file:///"+noticeRoot+"/");
        } else {
            registry.addResourceHandler("/notice-image/**")
                    .addResourceLocations("file:"+noticeRoot+"/");
        }
    }
}
