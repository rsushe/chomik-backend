package com.chomik.orders.config.quartz

import jakarta.annotation.PostConstruct
import org.quartz.JobKey.jobKey
import org.quartz.JobListener
import org.quartz.impl.matchers.KeyMatcher
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean

@Configuration
class JobListenerConfig(
    private val schedulerFactory: SchedulerFactoryBean,
    private val jobListener: JobListener
) {
    @PostConstruct
    fun addListener() {
        schedulerFactory.scheduler.listenerManager.addJobListener(jobListener, KeyMatcher.keyEquals(jobKey("SomeJob")))
    }
}
