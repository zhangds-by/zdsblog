package com.zhangds.zdsblog.security.config;

import com.zhangds.zdsblog.common.model.constants.SysContants;
import com.zhangds.zdsblog.security.jwt.JWTAuthenticationTokenFilter;
import com.zhangds.zdsblog.security.properties.SecurityProperties;
import com.zhangds.zdsblog.security.UserAuthenticationProvider;
import com.zhangds.zdsblog.security.UserPermissionEvaluator;
import com.zhangds.zdsblog.security.code.ValidateCodeGenerator;
import com.zhangds.zdsblog.security.code.img.ImageCodeFilter;
import com.zhangds.zdsblog.security.code.img.ImageCodeGenerator;
import com.zhangds.zdsblog.security.handler.*;
import com.zhangds.zdsblog.security.service.SelfUserDetailsService;
import com.zhangds.zdsblog.security.session.HandleExpiredSessionStrategy;
import com.zhangds.zdsblog.security.session.HandleInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * spring security 配置类
 * Create by zhangds
 * 2020-02-26 11:09
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义登录成功处理器
     */
    @Bean
    public UserLoginSuccessHandler userLoginSuccessHandler(){
        return new UserLoginSuccessHandler();
    }
    /**
     * 自定义登录失败处理器
     */
    @Bean
    public UserLoginFailureHandler userLoginFailureHandler(){
        return new UserLoginFailureHandler();
    }
    /**
     * 自定义注销成功处理器
     */
    public UserLogoutSuccessHandler userLogoutSuccessHandler(){
        return new UserLogoutSuccessHandler();
    }
    /**
     * 自定义暂无权限处理器
     */
    public UserAuthAccessDeniedHandler userAuthAccessDeniedHandler(){
        return new UserAuthAccessDeniedHandler();
    }
    /**
     * 自定义未登录的处理器
     */
    @Bean
    public UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler(){
        return new UserAuthenticationEntryPointHandler();
    }

    @Bean
    public UserLogoutHandler userLogoutHandler(){
        UserLogoutHandler logoutHandler = new UserLogoutHandler();
        logoutHandler.setSessionRegistry(sessionRegistry());
        return logoutHandler;
    }

    /**
     * 自定义登录逻辑验证器
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SelfUserDetailsService selfUserDetailsService;

   /**
     *加密器
     * @Author Sans
     * @CreateTime 2019/10/1 14:00
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 处理 rememberMe 自动登录认证
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    /**
     * 自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }

    @Bean
    public HandleInvalidSessionStrategy invalidSessionStrategy(){
        HandleInvalidSessionStrategy invalidSessionStrategy = new HandleInvalidSessionStrategy();
        invalidSessionStrategy.setSecurityProperties(securityProperties);
        return invalidSessionStrategy;
    }

    @Bean
    public HandleExpiredSessionStrategy expiredSessionStrategy(){
        return new HandleExpiredSessionStrategy();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 配置登录验证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        //这里可启用我们自己的登陆验证逻辑
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /**
     * 配置security的控制逻辑
     * @Author Sans
     * @CreateTime 2019/10/1 16:56
     * @Param  http 请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ImageCodeFilter imageCodeFilter = new ImageCodeFilter();

        http.exceptionHandling()
                .accessDeniedHandler(userAuthAccessDeniedHandler()) // 权限不足处理器
                .authenticationEntryPoint(userAuthenticationEntryPointHandler()) //未登录或token失效
                .and()
                    .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加图形证码校验过滤器
                    .formLogin() // 表单方式
                    //.loginPage(securityProperties.getLoginUrl()) // 未认证跳转 URL
                    .loginProcessingUrl(securityProperties.getCode().getImage().getLoginProcessingUrl()) // 处理登录认证 URL
                    .successHandler(userLoginSuccessHandler()) // 处理登录成功
                    .failureHandler(userLoginFailureHandler()) // 处理登录失败
                .and()
                    .rememberMe() // 添加记住我功能
                    .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                    .tokenValiditySeconds(securityProperties.getRememberMeTimeout()) // rememberMe 过期时间，单为秒
                    .userDetailsService(selfUserDetailsService) // 处理自动登录逻辑
                .and()
                    .sessionManagement() // 配置 session管理器
                    .invalidSessionStrategy(invalidSessionStrategy()) // 处理 session失效
                    .maximumSessions(securityProperties.getSession().getMaximumSessions()) // 最大并发登录数量
                    .expiredSessionStrategy(expiredSessionStrategy()) // 处理并发登录被踢出
                    .sessionRegistry(sessionRegistry()) // 配置 session注册中心
                .and()
                .and()
                    .logout() // 配置登出
                    .addLogoutHandler(userLogoutHandler()) // 配置登出处理器
                    .logoutUrl(securityProperties.getLogoutUrl()) // 处理登出 url
                    .logoutSuccessUrl("/") // 登出后跳转到 /
                    .deleteCookies("JSESSIONID") // 删除 JSESSIONID
                .and()
                    .authorizeRequests() // 授权配置
                    .antMatchers(JwtConfig.antMatchers.split(",")).permitAll() // 免认证静态资源路径
                    .antMatchers(
                            securityProperties.getLoginUrl(), // 登录路径
                            SysContants.REGIST_URL, // 用户注册 url
                            securityProperties.getCode().getImage().getCreateUrl() // 创建图片验证码路径
                    ).permitAll() // 配置免认证路径
                    .anyRequest()  // 所有请求
                    .authenticated() // 都需要认证
                .and()
                    .csrf().disable();
        // 基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT过滤器
        http.addFilter(new JWTAuthenticationTokenFilter(authenticationManager()));
    }

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }
}
