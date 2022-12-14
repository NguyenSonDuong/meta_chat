package com.kit.meta_chat.config;


import com.kit.meta_chat.jwt.JwtRequestFilter;
import com.kit.meta_chat.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class SecuriryConfig {


    private JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();

    @Autowired
    TokenRepository tokenRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        jwtRequestFilter.setVerificationTokenService(tokenRepository);
        http
                .csrf(cors->cors.disable())
                .authorizeRequests(auth-> {
                    try {
                        auth
                                .antMatchers(new String[]{"/user/login","/user/register"}).permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .formLogin(httpSecurityFormLoginConfigurer -> {
                                    httpSecurityFormLoginConfigurer.failureForwardUrl("/user/get");
                                    httpSecurityFormLoginConfigurer.failureHandler(authenticationFailureHandler());
                                });
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                })
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
