package com.chomik.orders.quartz

import com.chomik.orders.domain.SneakerCount
import com.chomik.orders.service.AdvertLockService
import com.chomik.orders.service.SneakerCountService
import org.apache.commons.lang3.mutable.MutableInt
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class FindExpiredReservationJob(
    private val advertLockService: AdvertLockService,
    private val sneakerCountService: SneakerCountService
) : QuartzJobBean() {
    private val log: Logger = LoggerFactory.getLogger(AdvertLockService::class.java)

    override fun executeInternal(context: JobExecutionContext) {
        log.info("Start FindExpiredReservationJob")

        val expiredLocks = advertLockService.deactivateExpiredAdvertLocks()

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
        log.info("End FindExpiredReservationJob")
    }
}
