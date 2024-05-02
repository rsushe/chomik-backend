package com.chomik.delivery.client.dto

import com.fakecdek.delivery.mock.model.dto.DeliveryAddressDto
import jakarta.validation.constraints.NotBlank

data class UserAddressDto (
    val id: String? = null,
    @field:NotBlank(message = "userId must not be null or blank")
    val userId: String,
    val address: DeliveryAddressDto
)
