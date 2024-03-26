package com.chomik.core.chomikgateway.domain

data class UserLoginRequest(
    val name: String,
    val password: String
)
