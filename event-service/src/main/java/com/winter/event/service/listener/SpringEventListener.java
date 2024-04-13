package com.winter.event.service.listener;


import com.winter.event.service.event.ChomikEvent;
import com.winter.event.service.exception.UnsupportedEventTypeException;
import com.winter.event.service.handler.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class SpringEventListener {

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    private static final Logger log = LoggerFactory.getLogger(SpringEventListener.class);
    private final Map<Class<? extends ChomikEvent>, EventHandler<? extends ChomikEvent>> handlerMapByChomikEventClass;

    public SpringEventListener(@Autowired List<EventHandler<? extends ChomikEvent>> handlerList) {
        handlerMapByChomikEventClass = new HashMap<>();
        for (EventHandler<? extends ChomikEvent> handler : handlerList) {

            Class<? extends ChomikEvent> chomikEventClass = getChomikEventGenericClass(handler);
            if (handlerMapByChomikEventClass.containsKey(chomikEventClass)) {
                throw new UnsupportedOperationException("Supported only 1 implementation handler on 1 ChomikEvent type class");
            }
            handlerMapByChomikEventClass.put(chomikEventClass, handler);
            log.info("Registered {} to handle events of type {}", handler.getClass(), chomikEventClass);
        }
    }

    private static Class<? extends ChomikEvent> getChomikEventGenericClass(EventHandler<? extends ChomikEvent> handler) {
        Class<?> generic = Resolver.resolveGeneric(handler.getClass(), EventHandler.class, 0);
        if (generic == null) {
            throw new UnsupportedOperationException("Couldn't get generic class(extended ChomikEvent) from the handler class %s".formatted(handler.toString()));
        }
        return (Class<? extends ChomikEvent>) generic;
    }

    @EventListener
    public void handle(ChomikEvent event) {
        executor.submit(() -> handleInternal(event));
    }

    protected <T extends ChomikEvent> void handleInternal(T event) {
        try {
            getHandler(event).handle(event);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    <T extends ChomikEvent> EventHandler<T> getHandler(T event) {
        if (!handlerMapByChomikEventClass.containsKey(event.getClass())) {
            throw new UnsupportedEventTypeException("Couldn't resolve handler for Event type: %s".formatted(event.getClass().toString()));
        }
        return (EventHandler<T>) handlerMapByChomikEventClass.get(event.getClass());
    }

    private static class Resolver {
        static Class<?> resolveGeneric(Class<?> clazz, Class<?> interfaceClass, int index) {
            Type[] types = clazz.getGenericInterfaces();
            for (Type type : types) {
                if (type instanceof ParameterizedType && ((ParameterizedType) type).getRawType().equals(interfaceClass)) {
                    return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[index];
                }
            }
            return null;
        }
    }
}


