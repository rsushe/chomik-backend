package com.chomik.core.chomikgateway.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserRegisterRequest(
    @JsonProperty val name: String,
    @JsonProperty val password: String,
    @JsonProperty val email: String?,
    @JsonProperty val phoneNumber: String?,
)
