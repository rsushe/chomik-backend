package com.chomik.orders.repository

import com.chomik.orders.client.dto.OrderStatus
import com.chomik.orders.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, String> {
    @Modifying
    @Query("UPDATE Order SET status = :status WHERE id in :ids")
    fun cancelOrderWhereIdIn(ids: List<String>, status: OrderStatus = OrderStatus.EXPIRED)
}
