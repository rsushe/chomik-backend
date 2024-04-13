package com.winter.event.service.handler;

import com.winter.event.service.event.ChomikEvent;
import com.winter.event.service.exception.EventHandleException;

public interface EventHandler<T extends ChomikEvent> {
    void handle(T event) throws EventHandleException;
}
