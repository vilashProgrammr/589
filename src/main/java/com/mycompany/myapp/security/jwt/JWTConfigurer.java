package com.mycompany.myapp.security.jwt;

import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    public JWTConfigurer() {
    }

}
