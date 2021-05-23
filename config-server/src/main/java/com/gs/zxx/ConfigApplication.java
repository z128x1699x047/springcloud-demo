package com.gs.zxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
post
* 加密：http://localhost:8085/encrypt?data=123456
* 解密：http://localhost:8085/decrypt
* */
@SpringBootApplication(scanBasePackages = "com.gs.zxx")
@EnableConfigServer
@EnableEurekaClient
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }
}
