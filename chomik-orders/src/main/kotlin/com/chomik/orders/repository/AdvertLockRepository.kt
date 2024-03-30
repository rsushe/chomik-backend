package com.chomik.orders.repository

import com.chomik.orders.domain.AdvertLock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface AdvertLockRepository : JpaRepository<AdvertLock, String> {
    @Modifying
    @Query(
        value = "UPDATE advert_lock SET is_active=false WHERE lock_time < :time and is_active RETURNING *",
        nativeQuery = true
    )
    fun dropAllLockOlderThan(@Param("time") time: Instant): List<AdvertLock>
}
