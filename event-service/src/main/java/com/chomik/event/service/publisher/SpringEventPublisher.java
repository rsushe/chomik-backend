package com.chomik.event.service.publisher;

import com.chomik.event.service.event.ChomikEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisher implements EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void publishEvent(ChomikEvent botEvent) {
        publisher.publishEvent(botEvent);
    }
}
