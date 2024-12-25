package com.project.shopapp.services.implement.base;

import com.project.shopapp.utils.LocalizationUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl {

    @Autowired
    protected LocalizationUtils localizationUtils;

    protected String message(String messageKey, Object... args) {
        return localizationUtils.getMessage(messageKey, args);
    }

}
