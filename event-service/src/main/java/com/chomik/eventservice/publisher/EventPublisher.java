package com.chomik.eventservice.publisher;

import com.chomik.eventservice.event.ChomikEvent;

public interface EventPublisher {
    void publishEvent(ChomikEvent botEvent);
}
