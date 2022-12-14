package com.kit.meta_chat.jwt.user_detail;

import com.kit.meta_chat.jwt.JwtUtil;
import com.kit.meta_chat.model.token.Token;
import com.kit.meta_chat.repository.TokenRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Setter
@Getter
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil = new JwtUtil();

    public JwtRequestFilter(TokenRepository verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    public JwtRequestFilter() {
    }

    @Autowired
    private TokenRepository verificationTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        UserPrincipal user = null;
        Token token = null;
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Token ")) {
            String jwt = authorizationHeader.substring(6);
            user = jwtUtil.getUserFromToken(jwt);
            token = verificationTokenService.findByToken(jwt);
        }
        if (null != user && null != token && token.getExpDate().after(new Date())) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Object role : user.getAuthorities().toArray()) {
                System.out.println(role);
                authorities.add(new SimpleGrantedAuthority(role.toString()));
            }
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
