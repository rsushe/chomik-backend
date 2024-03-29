package com.chomik.orders.scheduler

import com.chomik.orders.service.AdvertLockService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class FindExpiredReservationJob() : Job {

    @Autowired
    private lateinit var advertLockService: AdvertLockService;

    override fun execute(p0: JobExecutionContext?) {
        advertLockService.deactivateExpiredAdvertLocks()
    }
}