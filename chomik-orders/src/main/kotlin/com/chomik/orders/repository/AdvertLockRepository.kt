package com.chomik.orders.repository

import com.chomik.orders.domain.AdvertLock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AdvertLockRepository : JpaRepository<AdvertLock, String> {
}