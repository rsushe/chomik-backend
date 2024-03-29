package com.chomik.orders.repository

import com.chomik.orders.domain.SneakerCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SneakerCountRepository: JpaRepository<SneakerCount, String> {
    @Query("UPDATE SneakerCount SET count = :newCount WHERE advertId = :advertId")
    fun updateCount(@Param("advertId") advertId: String, @Param("newCount") newCount: Int)
}
