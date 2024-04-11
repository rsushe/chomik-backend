package com.chomik.eventservice.handler;

import com.chomik.eventservice.event.ChomikEvent;
import com.chomik.eventservice.exception.EventHandleException;

public interface EventHandler<T extends ChomikEvent> {
    void handle(T event) throws EventHandleException;
}
