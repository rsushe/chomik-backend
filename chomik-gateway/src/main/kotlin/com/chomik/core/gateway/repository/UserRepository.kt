package com.chomik.core.gateway.repository

import com.chomik.core.gateway.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByName(name: String): User?
}
