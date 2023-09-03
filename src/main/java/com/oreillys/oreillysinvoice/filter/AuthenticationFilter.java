package com.oreillys.oreillysinvoice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Log4j2
public class AuthenticationFilter extends OncePerRequestFilter {

    @Value("${filter.authenticate}")
    private boolean authenticate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("api-key");
        if(apiKey == null || !apiKey.equals("aBc123")){
            HttpServletResponse httpResp = (HttpServletResponse) response;
            httpResp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        log.info("Successfully authenticated request");
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        if (path.startsWith("/h2-console") || path.startsWith("/swagger-ui") || !authenticate) {
            return true;
        } else {
            return false;
        }
    }
}
