package com.pingpong.quoteBakery.sys.config;

import com.pingpong.quoteBakery.sys.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    // 무시할 요청들에 대한 패턴 정의
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**"); //정적 리소스 such as 이미지 or html
    }

    // HTTP 요청에 대한 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests() // 인증,인가
                    .requestMatchers("/v3/api-docs/**", "/swagger*/**", "/login", "/signup").permitAll() //이 요청들은 인증/인가 작업을 수행하지 않음.
                    .anyRequest().authenticated() //나머지 요청에 대해서는 인가(권한)는 필요하지 않지만 인증(사용자정보)은 진행함.
                .and()
                .formLogin().disable()
                .logout().invalidateHttpSession(true)
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
