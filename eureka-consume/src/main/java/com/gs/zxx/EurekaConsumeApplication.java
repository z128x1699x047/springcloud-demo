package com.gs.zxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class EurekaConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumeApplication.class,args);
    }

    @Bean
    @LoadBalanced//负载
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
