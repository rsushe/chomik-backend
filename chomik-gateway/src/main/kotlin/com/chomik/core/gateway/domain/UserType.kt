package com.chomik.core.gateway.domain

enum class UserType(val typeName: String, val canRegistrate: Boolean) {
    CLIENT("client", true),
    SELLER("seller", true),
    BANK("bank", false);
}
