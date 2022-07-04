package com.sys.exam.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sys.exam.vo.User;
import com.sys.exam.service.impl.UserService;
import com.sys.exam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;

    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    User hr = (User) authentication.getPrincipal();
                    hr.setPassword(null);
                    CommonResult<User> ok = CommonResult.success(hr, "登陆成功");
                    String s = new ObjectMapper().writeValueAsString(ok);
                    out.write(s);
                    out.flush();
                    out.close();
                }
        );
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    CommonResult<?> respBean = CommonResult.failed(exception.getMessage());
                    if (exception instanceof BadCredentialsException) {
                        respBean.setMessage("用户名或者密码输入错误，请重新输入!");
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                }
        );
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/doLogin");
        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        sessionStrategy.setMaximumSessions(1);
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/register","/v3/api-docs/**","/swagger-resources/**", "/webjars/**","/js/**", "/index.html","/doc.html/**", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode","/swagger-ui/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(urlAccessDecisionManager);
                        return object;
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write(new ObjectMapper().writeValueAsString(CommonResult.success("注销成功")));
                            out.flush();
                            out.close();
                        }
                )
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //没有认证时，在这里处理结果，不要重定向
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.setStatus(401);
                            PrintWriter out = resp.getWriter();
                            CommonResult<?> respBean = CommonResult.unauthorized("访问失败!");
                            if (authException instanceof InsufficientAuthenticationException) {
                                respBean.setMessage("请求失败，请联系管理员!");
                            }
                            out.write(new ObjectMapper().writeValueAsString(respBean));
                            out.flush();
                            out.close();
                        }
                );
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse resp = event.getResponse();
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(CommonResult.failed("多设备登录，请下线")));

        }), ConcurrentSessionFilter.class);

        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
