package com.fakecdek.delivery.mock.model.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateShipmentStatusRequest(
        @NotNull String shipmentId,
        @NotNull DeliveryStatus status
    ) {


}
