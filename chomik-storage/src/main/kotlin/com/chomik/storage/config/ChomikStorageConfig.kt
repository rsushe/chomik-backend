package com.chomik.storage.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionManager;


@Configuration
@EnableTransactionManagement
class ChomikStorageConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    fun userTransactionManager() : UserTransactionManager  {
        val userTransactionManager = UserTransactionManager()
        userTransactionManager.setTransactionTimeout(300)
        userTransactionManager.forceShutdown = false
        return userTransactionManager;
    }

    @Bean
    fun transactionManager() = JtaTransactionManager().apply {
        transactionManager = userTransactionManager()
        userTransaction = userTransactionManager()
    }

}