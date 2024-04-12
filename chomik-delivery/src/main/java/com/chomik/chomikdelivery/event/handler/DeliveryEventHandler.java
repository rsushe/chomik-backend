package com.chomik.chomikdelivery.event.handler;

import com.chomik.chomikdelivery.event.ApplyForDeliveryEvent;
import com.chomik.chomikdelivery.event.DeliveryEvent;
import com.chomik.eventservice.exception.EventHandleException;
import com.chomik.eventservice.handler.EventHandler;

public abstract class DeliveryEventHandler <T extends DeliveryEvent> implements EventHandler<T> {
}
