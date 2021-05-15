package com.gs.zxx.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        if (true) { //true 替换成需要检测的如，连接mysql成功返回true的接口
            return new Health.Builder(Status.UP).build();
        } else {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
