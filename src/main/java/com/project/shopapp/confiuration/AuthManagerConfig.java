package com.project.shopapp.confiuration;

import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.services.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class AuthManagerConfig
        implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {

    @Value("${api.prefix}")
    private String apiPrefix;

    private final IPermissionService permissionService;

    @Override
    public void customize(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
                    authorizationManagerRequestMatcherRegistry) {

        authorizationManagerRequestMatcherRegistry
                .requestMatchers(this.byPassToken())
                .permitAll()
                .requestMatchers(apiPrefix + "/**")
                .access(this::getAuthorizationDecision)
                .anyRequest()
                .permitAll();
    }

    private String[] byPassToken() {
        return permissionService.iPermissionByPassToken()
                .stream()
                .map(permission -> apiPrefix + permission.getResource())
                .toArray(String[]::new);
    }

    private AuthorizationDecision getAuthorizationDecision(Supplier<Authentication> authentication,
                                                           RequestAuthorizationContext requestContext) {
        return permissionService.iFindPermission(
                        requestContext.getRequest().getRequestURI().substring(apiPrefix.length()),
                        requestContext.getRequest().getMethod(),
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
