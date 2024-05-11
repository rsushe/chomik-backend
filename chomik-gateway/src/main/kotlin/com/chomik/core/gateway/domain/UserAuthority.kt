package com.chomik.core.gateway.domain

enum class UserAuthority {
    ADVERT,
    SNEAKERS,
    PROCESS_BANK_CALLBACK;

    companion object {
        const val ADVERT_AUTHORITY_NAME = "SCOPE_ADVERT"
        const val SNEAKERS_AUTHORITY_NAME = "SCOPE_SNEAKERS"
        const val PROCESS_BANK_CALLBACK_AUTHORITY_NAME = "SCOPE_PROCESS_BANK_CALLBACK"
    }
}
