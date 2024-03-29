package com.chomik.orders.repository

import com.chomik.orders.domain.AdvertLock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant


@Repository
interface AdvertLockRepository : JpaRepository<AdvertLock, String> {
    fun findAllByLockTimeBeforeAndActiveIsTrue(currentTime: Instant): List<AdvertLock>
}