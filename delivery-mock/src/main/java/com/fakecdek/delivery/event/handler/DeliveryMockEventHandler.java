package com.fakecdek.delivery.event.handler;

import com.winter.event.service.handler.EventHandler;
import com.fakecdek.delivery.event.DeliveryMockEvent;

public abstract class DeliveryMockEventHandler<T extends DeliveryMockEvent> implements EventHandler<T> {
}
