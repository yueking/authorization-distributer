package com.yueking.core.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 认证服务器 server
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager  authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    @Qualifier("jwtTokenStore")
    private TokenStore jwtTokenStore;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单提交
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
        //单点登录需要token验证通过
        security.tokenKeyAccess("isAuthenticated()");
        // security.tokenKeyAccess("permitAll()")///oauth/token_key公开
        //         .checkTokenAccess("permitAll()")///oauth/check_token公开
        //         .allowFormAuthenticationForClients();//允许表单认证
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("admin"))
                .scopes("all","readOnly","writeOnly")//访问范围标示，可以理解为 权限标示
                //令牌过期时间60秒
                .accessTokenValiditySeconds(60 * 60)
                //刷新令牌过期时间1小时
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30)
                //是否自动授权
                .autoApprove(true)
                .resourceIds("yueking_resource")
                .redirectUris("http://localhost:8080/callback")
                /**
                 * 授权码模式 简化模式 密码模式 客户端模式
                 * authorization_code refresh_token password client_credentials implicit
                 */
                .authorizedGrantTypes("authorization_code","refresh_token","password","client_credentials","implicit");
    }

    /**
     * 密码模式必须要重载方法需要
     * @param endpoints
     * @throws Exception
     * 默认端点地址
     * /oauth/authorize         授权
     * /oauth/token             令牌
     * /oauth/confirm_access    用户授权确认
     * /oauth/error             错误信息
     * /oauth/check_token       用于资源服务访问的令牌解析地址
     * /oauth/token_key         提供公有密匙端点,配合jwt
     * 配置项目
     * 1.客户端信息
     * 2.tokenService
     *
     */
    @Resource
    private AuthorizationServerTokenServices tokenServices;
    @Resource
    private AuthorizationCodeServices authorizationCodeServices;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        // start 设置jwt增强内容
        // TokenEnhancerChain chain = new TokenEnhancerChain();
        // List<TokenEnhancer> delegates = new LinkedList<>();
        // delegates.add(jwtTokenEnhancer);
        // delegates.add(jwtAccessTokenConverter);
        // chain.setTokenEnhancers(delegates);
        // endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService).tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter).tokenEnhancer(chain);
        // end 设置jwt增强内容

        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenServices)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
}
