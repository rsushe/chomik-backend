package com.chomik.orders.quartz.listener

import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.impl.triggers.CronTriggerImpl
import org.quartz.listeners.JobListenerSupport
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class JobListener(
    private val jdbcTemplate: NamedParameterJdbcTemplate
): JobListenerSupport() {
    override fun getName() = "orders job listener"

    override fun jobWasExecuted(context: JobExecutionContext?, jobException: JobExecutionException?) {
        val trigger = context?.trigger as CronTriggerImpl

        jdbcTemplate.update(
            """
                insert into qrtz_log(job_name, job_group, trigger_fire_time, job_finished_time, job_status, host_name)
                values (:job_name, :job_group, :trigger_fire_time, :job_finished_time, :job_status, :host_name)
            """.trimIndent(),
            MapSqlParameterSource()
                .addValue("job_name", trigger.name)
                .addValue("job_group", trigger.group)
                .addValue("trigger_fire_time", context.fireTime)
                .addValue("job_finished_time", Date.from(Instant.now()))
                .addValue("job_status", if (jobException == null) "OK" else jobException.message)
                .addValue("host_name", context.fireInstanceId)
        )
    }
}
