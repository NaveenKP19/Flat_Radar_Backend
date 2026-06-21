package com.PersonalProject.Flat.Radar.security;

import com.PersonalProject.Flat.Radar.service.impl.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        if(path.startsWith("/api/users/login")||
        path.startsWith("/api/users/register")||
        path.startsWith("/v3/api-docs")||
        path.startsWith("/swagger-ui")){
            filterChain.doFilter(request,response);
            return;

        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header = " +
                authHeader);

        String token=null;
        String email=null;

        if(authHeader !=null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);

            if(jwtUtil.validateToken(token)){
                email = jwtUtil.extractEmail(token);
                System.out.println("Authenticated user" + email);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);



                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        System.out.println("User:" + email);
        filterChain.doFilter(request,response);



    }
}
