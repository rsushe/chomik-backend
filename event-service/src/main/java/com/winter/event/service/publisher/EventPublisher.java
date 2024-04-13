package com.winter.event.service.publisher;

import com.winter.event.service.event.ChomikEvent;

public interface EventPublisher {
    void publishEvent(ChomikEvent botEvent);
}
