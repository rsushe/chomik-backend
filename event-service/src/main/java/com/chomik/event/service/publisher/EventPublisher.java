package com.chomik.event.service.publisher;

import com.chomik.event.service.event.ChomikEvent;

public interface EventPublisher {
    void publishEvent(ChomikEvent botEvent);
}
