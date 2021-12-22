package com.yueking.core.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class JwtTokenStoreConfig {
    /**
     * springSecurity 自动生成的token进转换为jwt
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置jwt密钥
        jwtAccessTokenConverter.setSigningKey("myJwtKey");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private TokenStore tokenStore;

    @Resource
    @Qualifier("jwtTokenStore")
    private TokenStore jwtTokenStore;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        // token start
        tokenServices.setTokenStore(tokenStore);
        // token end
        //jwt 增强 start
        // tokenServices.setTokenStore(jwtTokenStore);
        // TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // List<TokenEnhancer> delegates = new LinkedList<>();
        // delegates.add(jwtTokenEnhancer);
        // delegates.add(jwtAccessTokenConverter);
        // tokenEnhancerChain.setTokenEnhancers(delegates);
        // tokenServices.setTokenEnhancer(tokenEnhancerChain);
        //jwt 增强 end
        tokenServices.setAccessTokenValiditySeconds(7200);//令牌默认有效2小时
        tokenServices.setRefreshTokenValiditySeconds(259200);//刷新令牌有效3天
        return tokenServices;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
}
