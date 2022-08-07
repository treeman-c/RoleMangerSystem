package com.treeman.security;

import com.treeman.filter.ValidFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级安全验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {


     @Autowired
     private UserRoleSecurity userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，会报错。
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authenticationProvider(authenticationProvider())
//                .httpBasic()
//                //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
//                .authenticationEntryPoint((request,response,authException) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",403);
//                    map.put("message","未登录");
////                    out.write(objectMapper.writeValueAsString(map));
////                    out.flush();
//                    out.close();
//                })
//
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated() //必须授权才能范围
//
//                .and()
//                .formLogin() //使用自带的登录
//                .permitAll()
//                //登录失败，返回json
//                .failureHandler((request,response,ex) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",401);
//                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
//                        map.put("message","用户名或密码错误");
//                    } else if (ex instanceof DisabledException) {
//                        map.put("message","账户被禁用");
//                    } else {
//                        map.put("message","登录失败!");
//                    }
////                    out.write(objectMapper.writeValueAsString(map));
////                    out.flush();
//                    out.close();
//                })
//                //登录成功，返回json
//                .successHandler((request,response,authentication) -> {
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",200);
//                    map.put("message","登录成功");
//                    map.put("data",authentication);
//                    response.setContentType("application/json;charset=utf-8");
////                    PrintWriter out = response.getWriter();
////                    out.write(objectMapper.writeValueAsString(map));
////                    out.flush();
////                    out.close();
//                })
//                .and()
//                .exceptionHandling()
//                //没有权限，返回json
//                .accessDeniedHandler((request,response,ex) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",403);
//                    map.put("message", "权限不足");
////                    out.write(objectMapper.writeValueAsString(map));
////                    out.flush();
//                    out.close();
//                })
//                .and()
//                .logout()
//                //退出成功，返回json
//                .logoutSuccessHandler((request,response,authentication) -> {
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",200);
//                    map.put("message","退出成功");
//                    map.put("data",authentication);
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
////                    out.write(objectMapper.writeValueAsString(map));
////                    out.flush();
//                    out.close();
//                })
//                .permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
