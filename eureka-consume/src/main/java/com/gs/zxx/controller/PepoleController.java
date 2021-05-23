package com.gs.zxx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pepole")
public class PepoleController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String APP = "eureka-provider%s";

    //测试配置中心
    @Value("${username}")
    private String keyValue;

    @Autowired
    private Environment environment;

    @GetMapping("/getUser")
    public String getUser(){
        String url = String.format(APP,"/user/getUser");
        String value = restTemplate.getForObject(url,String.class);
        System.out.println("@Value(username)="+keyValue);
        System.out.println("environment="+environment.getProperty("username"));
        return value;
    }
}
