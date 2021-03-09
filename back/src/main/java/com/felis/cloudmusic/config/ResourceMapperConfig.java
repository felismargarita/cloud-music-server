package com.felis.cloudmusic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceMapperConfig implements WebMvcConfigurer {

    @Value("${resource.song.path}")
    private String songPath;

    @Value("${resource.picture.path}")
    private String picturePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/picture/**").addResourceLocations("file:"+picturePath);
        registry.addResourceHandler("/resource/song/**").addResourceLocations("file:"+songPath);
    }
}