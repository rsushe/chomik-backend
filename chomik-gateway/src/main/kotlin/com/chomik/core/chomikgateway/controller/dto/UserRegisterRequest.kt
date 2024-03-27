package com.chomik.core.chomikgateway.controller.dto

import com.chomik.core.chomikgateway.domain.UserType
import com.fasterxml.jackson.annotation.JsonProperty

data class UserRegisterRequest(
    @JsonProperty val name: String,
    @JsonProperty val password: String,
    @JsonProperty val email: String?,
    @JsonProperty val phoneNumber: String?,
    @JsonProperty val userType: UserType
)
