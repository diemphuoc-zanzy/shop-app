package com.project.shopapp.confiuration;

import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.services.IPermissionService;
import com.project.shopapp.utils.JwtTokenFilterUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.function.Supplier;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Value("${api.prefix}")
    private String apiPrefix;

    private final JwtTokenFilterUtil jwtTokenFilterUtil;

    private final IPermissionService permissionService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilterUtil, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authManager -> {
                    authManager
                            .requestMatchers(this.byPassToken())
                            .permitAll()
                            .requestMatchers(apiPrefix + "/**")
                            .access((authentication, auth) ->
                                getAuthorizationDecision(authentication, auth.getRequest())
                            )
                            .anyRequest()
                            .permitAll();
                })
                .build();
    }

    private String[] byPassToken() {
        return permissionService.iPermissionByPassToken()
                .stream()
                .map(permission -> apiPrefix + permission.getResource())
                .toArray(String[]::new);
    }

    private AuthorizationDecision getAuthorizationDecision(Supplier<Authentication> authentication,
                                                           HttpServletRequest request) {
        return permissionService.iFindPermission(
                        request.getRequestURI().substring(apiPrefix.length()),
                        request.getMethod(),
                        getRoleIdFromAuthentication(authentication)
                )
                .map(permission -> new AuthorizationDecision(true))
                .orElse(new AuthorizationDecision(false));
    }

    private String getRoleIdFromAuthentication(Supplier<Authentication> authenticationSupplier) {
        return authenticationSupplier.get()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Role ID not found"));
    }
}
