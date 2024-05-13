package com.unboxit.bnichecking.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unboxit.bnichecking.exception.TokenInvalidException;
import com.unboxit.bnichecking.exception.UserNotAllowedException;
import com.unboxit.bnichecking.service.AdminDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private static String jwtSecretKey;
    private final AdminDetailService adminDetailsService;
    private final ObjectMapper objectMapper;

    public JwtAuthFilter( @Value("${jwt.secretKey}") String jwtSecretKey, AdminDetailService adminDetailsService, ObjectMapper objectMapper) {
        this.adminDetailsService = adminDetailsService;
        this.objectMapper = objectMapper;
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");

            String token = null;
            String username = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = extractUsername(token);
            }

//      If the accessToken is null. It will pass the request to next filter in the chain.
//      Any login and signup requests will not have jwt token in their header, therefore they will be passed to next filter chain.
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

//       If any accessToken is present, then it will validate the token and then authenticate the request in security context
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = adminDetailsService.loadUserByUsername(username);
                if (validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (io.jsonwebtoken.JwtException e) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new TokenInvalidException("token is invalid: " + e.getMessage()).toString());
        }
    }

    public static String extractUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public static Claims getTokenBody(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private static boolean isTokenExpired(String token) {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().before(new Date());
    }

    public static void checkAdminToken(String token) {
        Claims claims = getTokenBody(token);
        if (!claims.get("role").toString().equals("admin")) {
            throw new UserNotAllowedException("");
        }
    }

    public static Long getAdminIdFromToken(String token) {
        Claims claims = getTokenBody(token);
        return Long.parseLong(claims.get("admin_id").toString());
    }

    public static Long getUserIdFromToken(String token) {
        Claims claims = getTokenBody(token);
        return Long.parseLong(claims.get("user_id").toString());
    }
}
