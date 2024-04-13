package com.fakecdek.delivery.mock.model.dto;

public enum DeliveryStatus {
    CREATED("Зарегистрирована"),
    TAKEN_INTO_PROCESS("Принята службой доставки"),
    PICKED_UP_BY_COURIER("Принята курьером от отправителя"),
    SORTING_IN_SENDER_CITY("В центре сортировки города отправителя"),
    IN_TRANSIT("В пути"),
    SORTING_IN_RECEIVER_CITY("В центре сортировки города получателя"),
    IN_COURIER_DELIVERY("Принята курьером для доставки получателю"),
    RECEIVED_BY_RECIPIENT("Принята получателем");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
