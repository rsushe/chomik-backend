package com.chomik.orders.scheduler

import com.chomik.orders.scheduler.config.AutoWiringSpringBeanJobFactory
import jakarta.annotation.PostConstruct
import org.quartz.JobDetail
import org.quartz.SimpleTrigger
import org.quartz.Trigger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean
import org.springframework.scheduling.quartz.SpringBeanJobFactory
import javax.sql.DataSource

@Configuration
@EnableAutoConfiguration
class SpringQrtzScheduler {

    private val logger: Logger = LoggerFactory.getLogger(SpringQrtzScheduler::class.java)

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Bean
    fun springBeanJobFactory(): SpringBeanJobFactory {
        val jobFactory = AutoWiringSpringBeanJobFactory()
        logger.debug("Configuring Job factory")
        jobFactory.setApplicationContext(applicationContext)
        return jobFactory
    }

    @Bean
    fun scheduler(trigger: Trigger, job: JobDetail, @QuartzDataSource quartzDataSource: DataSource): SchedulerFactoryBean {
        val schedulerFactory = SchedulerFactoryBean()
        schedulerFactory.setConfigLocation(ClassPathResource("quartz.properties"))
        logger.debug("Setting the Scheduler up")
        schedulerFactory.setJobFactory(springBeanJobFactory())
        schedulerFactory.setJobDetails(job)
        schedulerFactory.setTriggers(trigger)
        // Comment the following line to use the default Quartz job store.
        schedulerFactory.setDataSource(quartzDataSource)
        return schedulerFactory
    }

    @Bean
    fun jobDetail(): JobDetailFactoryBean {
        val jobDetailFactory = JobDetailFactoryBean()
        jobDetailFactory.setJobClass(FindExpiredReservationJob::class.java)
        jobDetailFactory.setName("Qrtz_Job_Detail")
        jobDetailFactory.setDescription("Invoke Sample Job service...")
        jobDetailFactory.setDurability(true)
        return jobDetailFactory
    }

    @Bean
    fun trigger(job: JobDetail): SimpleTriggerFactoryBean {
        val trigger = SimpleTriggerFactoryBean()
        trigger.setJobDetail(job)
        val frequencyInSec = 10
        logger.info("Configuring trigger to fire every {} seconds", frequencyInSec)
        trigger.setRepeatInterval(frequencyInSec * 1000L)
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)
        trigger.setName("Qrtz_Trigger")
        return trigger
    }

    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource")
    fun quartzDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}
