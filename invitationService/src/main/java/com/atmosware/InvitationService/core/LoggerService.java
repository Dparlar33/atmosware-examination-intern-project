package com.atmosware.InvitationService.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerService {

    private final Logger log = LoggerFactory.getLogger(Logger.class);

    public void info(String message) {
        log.info(message);
    }
}
