package com.chomik.storage.client.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class SaveSneakersRequest(
    @field:NotBlank(message = "Model must not be blank")
    val model: String,

    @field:NotNull(message = "Brand must not be null")
    val brand: Brand,

    @field:NotNull(message = "Size must not be null")
    @field:Positive(message = "Size must be positive")
    @field:Max(value = 100, message = "Size must be less than or equal to 100")
    val size: Double,

    @field:NotNull(message = "Color must not be null")
    val color: Color,

    @field:NotNull(message = "Condition must not be null")
    val condition: Condition
)