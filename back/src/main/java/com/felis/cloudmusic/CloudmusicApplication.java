package com.felis.cloudmusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.felis.cloudmusic.mapper")
public class CloudmusicApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudmusicApplication.class, args);
	}

}
