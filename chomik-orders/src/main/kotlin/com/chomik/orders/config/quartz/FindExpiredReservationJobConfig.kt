package com.chomik.orders.config.quartz

import com.chomik.orders.quartz.FindExpiredReservationJob
import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindExpiredReservationJobConfig(
    @Value("\${expired.reservation.job.cron}") private val jobCron: String
) {
    @Bean
    fun findExpiredReservationJobDetail(): JobDetail = JobBuilder
        .newJob(FindExpiredReservationJob::class.java)
        .withIdentity("FindExpiredReservationJob", "PERMANENT")
        .withDescription("Find and drop all expired locks")
        .storeDurably(true)
        .build()

    @Bean
    fun trigger(job: JobDetail): Trigger = TriggerBuilder.newTrigger()
        .forJob(findExpiredReservationJobDetail())
        .withIdentity("FindExpiredReservationJob", "PERMANENT")
        .withSchedule(CronScheduleBuilder.cronSchedule(jobCron))
        .build()
}