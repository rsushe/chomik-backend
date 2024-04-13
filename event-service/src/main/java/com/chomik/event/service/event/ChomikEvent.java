package com.chomik.event.service.event;

import java.util.UUID;

public abstract class ChomikEvent {

    private final String eventKey;

    public ChomikEvent() {
        eventKey = UUID.randomUUID().toString();
    }

    public String getEventKey() {
        return eventKey;
    }

    public String getTitle(){
        return "Событие %s".formatted(getType());
    }

    public String getBody(){
        return "Произошло событие %s. Уникальный ключ %s".formatted(getType(), getEventKey());
    }

    public String getType(){
        return this.getClass().getSimpleName();
    }
}
