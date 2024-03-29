package com.chomik.storage.service.dto

import com.chomik.storage.domain.AdvertStatus
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class SaveAdvertRequest(

    @field:NotBlank(message = "Price must not be blank")
    val sneakerId: String,

    @field:NotBlank(message = "Price must not be null")
    val sellerId: String,

    @field:NotNull(message = "Price must not be null")
    val status: AdvertStatus,

    @field:NotNull(message = "Price must not be null")
    @field:Positive(message = "Price must be positive")
    val price: Double,

    @field:NotNull(message = "Price must not be null")
    val active: Boolean,

    @field:NotNull(message = "sneakerCount must not be null")
    @field:Max(value = 100, message = "sneakerCount must be less than 100")
    @field:Min(value = 1, message = "sneakerCount must be more than 0")
    val sneakerCount: Int,

    val description: String?
)