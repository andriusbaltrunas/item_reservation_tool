package com.item.reservation.tool.service.message.impl;

import com.item.reservation.tool.service.message.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String message(String key) {
        String message = StringUtils.EMPTY;
        String temp = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        if (StringUtils.isNotBlank(temp)) {
            message = temp;
        }
        return message;
    }
}
