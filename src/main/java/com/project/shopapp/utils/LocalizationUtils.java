package com.project.shopapp.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class LocalizationUtils {
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public String getMessage(String messageKey, Object... args) {
        HttpServletRequest request = WebUtils.getCurrentHttpServletRequest();
        Locale locale = localeResolver.resolveLocale(request);

        String defaultMessage = "Message not found for key: " + messageKey;
        return messageSource.getMessage(messageKey, args, defaultMessage, locale);
    }
}
