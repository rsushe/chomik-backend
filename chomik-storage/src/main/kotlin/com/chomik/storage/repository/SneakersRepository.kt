package com.chomik.storage.repository

import com.chomik.storage.domain.Sneakers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SneakersRepository : JpaRepository<Sneakers, String> {
}