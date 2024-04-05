package com.chomik.orders.repository

import com.chomik.orders.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface OrderRepository : JpaRepository<Order, String> {
    @Modifying
    @Query(
        value = "UPDATE \"order\" SET status = 'EXPIRED' WHERE creation_time < :time and status = 'WAIT_PAYMENT' RETURNING *",
        nativeQuery = true
    )
    fun cancelAllOrdersOlderThan(time: Instant): List<Order>

    @Query("SELECT SUM(sneakerCount) FROM Order WHERE advertId = :advertId AND status = 'WAIT_PAYMENT'")
    fun countWaitingPaymentOrdersOnAdvert(advertId: String): Int?
}
