package com.chomik.core.gateway.controller.dto

import com.chomik.core.gateway.domain.UserType
import com.fasterxml.jackson.annotation.JsonProperty

data class UserRegisterRequest(
    @JsonProperty val name: String,
    @JsonProperty val password: String,
    @JsonProperty val email: String?,
    @JsonProperty val phoneNumber: String?,
    @JsonProperty val userType: UserType
)
