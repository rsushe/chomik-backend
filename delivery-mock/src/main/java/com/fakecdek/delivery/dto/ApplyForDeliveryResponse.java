package com.fakecdek.delivery.dto;

public record ApplyForDeliveryResponse(String message, ResponseStatus status, TrackLinkDto trackLink) {
    public enum ResponseStatus {
        TAKEN,
        REJECTED
    }
}
