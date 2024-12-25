package com.project.shopapp.utils;

import com.project.shopapp.common.constant.Constant;
import com.project.shopapp.models.Permission;
import com.project.shopapp.models.User;
import com.project.shopapp.services.IPermissionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilterUtils extends OncePerRequestFilter {

    @Value("${api.prefix}")
    private String apiPrefix;

    private final UserDetailsService userDetailsService;
    private final IPermissionService permissionService;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (isBypassToken(request) || isCheckTokenValid(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access");
    }

    private boolean isCheckTokenValid(HttpServletRequest request) {
        try {
            final String authHeader = request.getHeader(Constant.HEADER.AUTHORIZATION);
            String bearer = Constant.HEADER.BEARER;
            if (authHeader == null || !authHeader.startsWith(bearer))
                return false;

            final String token = authHeader.substring(bearer.length());
            final String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

            if (phoneNumber == null || SecurityContextHolder.getContext().getAuthentication() != null) {
                return false;
            }

            User userDetails = (User) userDetailsService.loadUserByUsername(phoneNumber);
            if (!jwtTokenUtils.validateToken(token, userDetails)) {
                return false;
            }

            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(
                            userDetails,
                    null,
                            userDetails.getAuthorities()
                    );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    private boolean isBypassToken(HttpServletRequest request) {
        return permissionService.iPermissionByPassToken().stream()
                .anyMatch(
                        permission -> validatePathMethod(request, permission)
                );
    }

    private boolean validatePathMethod(HttpServletRequest request, Permission permission) {
        String path = permission.getResource();
        String method = permission.getMethod();

        return request.getServletPath().contains(path)
                && request.getMethod().equals(method);
    }
}
