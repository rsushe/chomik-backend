package com.chomik.storage.repository

import com.chomik.storage.domain.Advert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AdvertRepository : JpaRepository<Advert, String> {
    fun findBySellerId(sellerId: String): List<Advert>
}