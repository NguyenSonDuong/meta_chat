package com.kit.meta_chat.config;


import com.kit.meta_chat.jwt.user_detail.JwtRequestFilter;
import com.kit.meta_chat.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .authorizeRequests(auth->auth
                        .mvcMatchers(new String[]{"/user/login","/user/register"}).permitAll()
                        .anyRequest().authenticated().and())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
