package com.chomik.orders.quartz

import com.chomik.orders.service.OrderService
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class FindExpiredReservationJob(
    private val orderService: OrderService,
    @Value("\${expired.reservation.job.lock.timeout}") private val lockTimeoutInSeconds: Long,
) : QuartzJobBean() {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun executeInternal(context: JobExecutionContext) {
        log.info("Start FindExpiredReservationJob")

        orderService.cancelExpiredOrders(lockTimeoutInSeconds)

        log.info("End FindExpiredReservationJob")
    }
}
