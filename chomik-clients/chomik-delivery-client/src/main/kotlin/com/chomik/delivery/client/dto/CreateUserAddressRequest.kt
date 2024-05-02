package com.chomik.delivery.client.dto

import com.fakecdek.delivery.mock.model.dto.DeliveryAddressDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateUserAddressRequest(
    @NotBlank(message = "userId must not be null or blank")
    val userId: String,
    @NotNull(message = "address must not be null or blank")
    @Valid
    val address: DeliveryAddressDto
)
