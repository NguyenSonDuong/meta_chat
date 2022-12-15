package com.kit.meta_chat.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kit.meta_chat.jwt.JwtRequestFilter;
import com.kit.meta_chat.repository.TokenRepository;
import com.kit.meta_chat.responsive.BaseRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecuriryConfig {


    private JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();

    @Autowired
    TokenRepository tokenRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        jwtRequestFilter.setVerificationTokenService(tokenRepository);
        http
                .csrf(cors->cors.disable())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(new String[]{"/user/login","/user/register"}).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(new Customizer<ExceptionHandlingConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(ExceptionHandlingConfigurer<HttpSecurity> httpSecurityExceptionHandlingConfigurer) {
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new AuthenticationEntryPoint() {
                            @Override
                            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                                String json = ow.writeValueAsString(BaseRespo.builder().code(-1).title("Authentication fails").content(null).status("error").build());
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                response.getWriter().write(json);
                            }
                        });
                        httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(new AccessDeniedHandler() {
                            @Override
                            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                                String json = ow.writeValueAsString(BaseRespo.builder().code(-1).title("Authentication fails").content(null).status("error").build());
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                response.getWriter().write(json);
                            }
                        });

                    }
                });
        return http.build();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
