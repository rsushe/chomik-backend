package com.chomik.eventservice.handler;

import com.chomik.eventservice.event.ChomikEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultLogEventHandler<T extends ChomikEvent> implements EventHandler<T> {
    private static final Logger log = LoggerFactory.getLogger(DefaultLogEventHandler.class);

    @Override
    public void handle(final T event) {
        log.info("Handle event: %s".formatted(event.toString()));
    }
}