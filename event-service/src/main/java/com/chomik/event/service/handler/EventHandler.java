package com.chomik.event.service.handler;

import com.chomik.event.service.event.ChomikEvent;
import com.chomik.event.service.exception.EventHandleException;

public interface EventHandler<T extends ChomikEvent> {
    void handle(T event) throws EventHandleException;
}
