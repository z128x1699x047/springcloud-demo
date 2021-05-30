package com.gs.zxx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer //开启认证服务注解 并且继承AuthorizationServerConfigurerAdapter
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    //token存储方式自己实现
    public TokenStore tokenStore(){
        return new MyRedisTokenStore(connectionFactory);
    }

    //配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //鉴权管理
                .authenticationManager(authenticationManager)
                //token绑定用户--密码模式，授权码模式
                .userDetailsService(userDetailsService)
                //token的保存方式
                .tokenStore(tokenStore())
                //允许访问的方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);

    }

    //设置客户端的基本信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String seret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        clients.inMemory()
                //.jdbc() 存到数据库
                .inMemory() //存到内存
                .withClient("oauth2.0-client") //appid
                .secret(seret) //appsecurt
                .authorizedGrantTypes("password","refresh_token") //鉴权模式
                .resourceIds("")
                .scopes("server")
                .authorities("password")
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }
}
