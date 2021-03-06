package com.felis.cloudmusic.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/reader")
public class MusicReaderController {

    @Value("${resource.path}")
    private String path;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:/Users/hongweiyin/Documents/github-workspace/music-website/music-server/img/songPic/");
            registry.addResourceHandler("/song/**").addResourceLocations("file:D://");
        }
    }
}
