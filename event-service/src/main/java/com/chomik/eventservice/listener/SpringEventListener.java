package com.chomik.eventservice.listener;


import com.chomik.eventservice.event.ChomikEvent;
import com.chomik.eventservice.handler.DefaultLogEventHandler;
import com.chomik.eventservice.handler.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.core.ResolvableType.forClass;

@Component
public class SpringEventListener {

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final Logger log = LoggerFactory.getLogger(SpringEventListener.class);

    private EventHandler<? extends ChomikEvent> defaultLogHandler;
    private final Map<Class<? extends ChomikEvent>, EventHandler<? extends ChomikEvent>> handlerMapBychomikEventClass = new HashMap<>();


    public SpringEventListener(@Autowired final List<EventHandler<? extends ChomikEvent>> handlerList) {
        for (final EventHandler<? extends ChomikEvent> handler : handlerList) {
            if (handler instanceof DefaultLogEventHandler) {
                defaultLogHandler = handler;
                continue;
            }
            final Class<? extends ChomikEvent> chomikEventClass = getchomikEventGenericClass(handler);
            if (handlerMapBychomikEventClass.containsKey(chomikEventClass)) {
                throw new UnsupportedOperationException("Supported only 1 implementation handler on 1 ChomikEvent type class");
            }
            this.handlerMapBychomikEventClass.put(chomikEventClass, handler);
            log.info("Registered %s to handle events of type %s".formatted(handler.getClass(), chomikEventClass));
        }
    }

    private static Class<? extends ChomikEvent> getchomikEventGenericClass(final EventHandler<? extends ChomikEvent> handler) {
        Class<?> generic = forClass(handler.getClass()).as(EventHandler.class).resolveGeneric(0);

        if (generic == null) {
            throw new UnsupportedOperationException(
                    "Couldn't get generic class(extended ChomikEvent) from the handler class%s".formatted(handler.toString()));
        }

        return (Class<? extends ChomikEvent>) forClass(handler.getClass()).as(EventHandler.class).resolveGeneric(0);
    }

    @EventListener
    public void handle(final ChomikEvent event) {
        executor.submit(() -> handleInternal(event));
    }

    protected <T extends ChomikEvent> void handleInternal(T event) {

        try {
            getHandler(event).handle(event);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    <T extends ChomikEvent> EventHandler<T> getHandler(final T event) {
        if (!handlerMapBychomikEventClass.containsKey(event.getClass())) {
            return (EventHandler<T>) defaultLogHandler;
        }
        return (EventHandler<T>) handlerMapBychomikEventClass.get(event.getClass());
    }
}
