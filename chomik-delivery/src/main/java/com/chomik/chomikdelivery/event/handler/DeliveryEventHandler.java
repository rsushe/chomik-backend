package com.chomik.chomikdelivery.event.handler;

import com.chomik.chomikdelivery.event.DeliveryEvent;
import com.winter.event.service.handler.EventHandler;

public abstract class DeliveryEventHandler <T extends DeliveryEvent> implements EventHandler<T> {
}
