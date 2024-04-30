package com.chomik.core.gateway.domain

enum class UserType(val typeName: String) {
    CLIENT("client"),
    SELLER("seller");

    companion object {
        const val CLIENT_AUTHORITY_NAME = "SCOPE_client"
        const val SELLER_AUTHORITY_NAME = "SCOPE_seller"
    }
}
