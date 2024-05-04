package com.chomik.core.gateway.domain

enum class UserType(val typeName: String, val canRegistrate: Boolean) {
    CLIENT("client", true),
    SELLER("seller", true),
    BANK("bank", false);

    companion object {
        const val CLIENT_AUTHORITY_NAME = "SCOPE_client"
        const val SELLER_AUTHORITY_NAME = "SCOPE_seller"
    }
}
