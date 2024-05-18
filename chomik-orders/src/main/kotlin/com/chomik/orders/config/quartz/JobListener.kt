package com.chomik.orders.config.quartz

import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.listeners.JobListenerSupport
import org.springframework.stereotype.Component

@Component
class JobListener: JobListenerSupport() {
    override fun getName() = "orders job listener"

    override fun jobWasExecuted(context: JobExecutionContext?, jobException: JobExecutionException?) {
        log.info("Job was executed: {}, {}", context?.jobDetail.toString(), context?.mergedJobDataMap.toString())

        if (jobException != null) {
            log.info("Job was executed: {}, {}", context?.jobDetail.toString(), context?.mergedJobDataMap.toString())
        }
    }
}
