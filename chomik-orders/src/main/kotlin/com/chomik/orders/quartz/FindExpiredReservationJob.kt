package com.chomik.orders.quartz

import com.chomik.orders.domain.SneakerCount
import com.chomik.orders.service.AdvertLockService
import com.chomik.orders.service.OrderService
import com.chomik.orders.service.SneakerCountService
import org.apache.commons.lang3.mutable.MutableInt
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class FindExpiredReservationJob(
    private val advertLockService: AdvertLockService,
    private val sneakerCountService: SneakerCountService,
    private val orderService: OrderService,
    @Value("\${expired.reservation.job.lock.timeout}") private val lockTimeoutInSeconds: Long,
) : QuartzJobBean() {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun executeInternal(context: JobExecutionContext) {
        log.info("Start FindExpiredReservationJob")

        val expiredLocks = advertLockService.deactivateExpiredAdvertLocks(lockTimeoutInSeconds)

        val expiredCountsByAdvertId =
            expiredLocks.groupingBy { it.advertId }.aggregate { _, accumulator: MutableInt?, element, first ->
                if (first) {
                    MutableInt(element.lockedCount)
                }
                else {
                    accumulator?.add(element.lockedCount)
                    accumulator
                }
            }

        sneakerCountService.addCountBatch(expiredCountsByAdvertId.map { SneakerCount(it.key, it.value!!.value) })

        orderService.cancelOrderWhereIdIn(expiredLocks.map { it.orderId })

        log.info("End FindExpiredReservationJob")
    }
}
