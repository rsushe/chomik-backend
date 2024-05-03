package com.chomik.storage.client.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class SaveAdvertRequest(

    @JsonProperty
    @field:NotBlank(message = "Price must not be blank")
    val sneakerId: String,

    @JsonProperty
    @field:NotBlank(message = "Price must not be null")
    val sellerId: String,

    @JsonProperty
    @field:NotNull(message = "Price must not be null")
    val status: AdvertStatus,

    @JsonProperty
    @field:NotNull(message = "Price must not be null")
    @field:Positive(message = "Price must be positive")
    val price: Double,

    @JsonProperty
    @field:NotNull(message = "Price must not be null")
    val active: Boolean,

    @field:NotNull(message = "sneakerCount must not be null")
    @field:Max(value = 100, message = "sneakerCount must be less than 100")
    @field:Min(value = 1, message = "sneakerCount must be more than 0")
    val sneakerCount: Int,

    @JsonProperty
    @field:NotBlank(message = "Seller address id must not be null")
    val sellerAddressId: String,

    val description: String?,



)