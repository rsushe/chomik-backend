package com.chomik.core.gateway.config

import com.chomik.core.gateway.domain.UserAuthority
import com.chomik.core.gateway.domain.UserType
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("security")
data class UserAuthorities(
    val authorities: Map<UserType, List<UserAuthority>>
)
