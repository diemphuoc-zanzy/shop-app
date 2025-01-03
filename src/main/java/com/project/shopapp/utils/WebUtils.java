package com.project.shopapp.utils;

import com.project.shopapp.confiuration.exception.UnauthorizedAccessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class WebUtils {

    public static HttpServletRequest getCurrentHttpServletRequest() {
        return Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()))
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new UnauthorizedAccessException("HttpServletRequest is null. Cannot proceed."));
    }

    public static HttpServletResponse getCurrentHttpServletResponse() {
        return Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()))
                .map(ServletRequestAttributes::getResponse)
                .orElseThrow(() -> new UnauthorizedAccessException("HttpServletResponse is null. Cannot proceed."));
    }
}
