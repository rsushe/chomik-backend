package com.chomik.orders.quartz

import com.chomik.orders.facade.OrderFacade
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class FindExpiredReservationJob(
    private val orderFacade: OrderFacade,
    @Value("\${expired.reservation.job.lock.timeout}") private val lockTimeoutInSeconds: Long,
) : QuartzJobBean() {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun executeInternal(context: JobExecutionContext) {
        orderFacade.cancelExpiredOrders(lockTimeoutInSeconds)
    }
}
