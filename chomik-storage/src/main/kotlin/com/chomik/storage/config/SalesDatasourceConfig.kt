package com.chomik.storage.config

import com.atomikos.jdbc.AtomikosDataSourceBean
import com.chomik.storage.repository.sales.SalesRepository
import jakarta.persistence.EntityManagerFactory
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*

@Configuration
@EnableJpaRepositories(
    basePackageClasses = [SalesRepository::class],
    entityManagerFactoryRef = "salesEntityManager",
    transactionManagerRef = "transactionManager"
)
class SalesDatasourceConfig {
    @Bean
    fun salesLiquibase(
        @Value("\${datasource.sales.database}") dbName: String,
        @Value("\${datasource.sales.username}") username: String,
        @Value("\${datasource.sales.password}") pwd: String,
        @Value("\${datasource.sales.server}") server: String,
        @Value("\${datasource.sales.port}") port: String,
        @Value("\${datasource.sales.liquibase.change-log}") liquibaseChangeLog: String
    ): SpringLiquibase {
        return SpringLiquibase().apply {
            dataSource = salesDataSource(dbName, username, pwd, server, port)
            changeLog = liquibaseChangeLog
        }
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    fun salesDataSource(
        @Value("\${datasource.sales.database}") dbName: String,
        @Value("\${datasource.sales.username}") username: String,
        @Value("\${datasource.sales.password}") pwd: String,
        @Value("\${datasource.sales.server}") server: String,
        @Value("\${datasource.sales.port}") port: String
    ): AtomikosDataSourceBean {
        val dataSource = AtomikosDataSourceBean()
        dataSource.uniqueResourceName = "db2"
        dataSource.xaDataSourceClassName = "org.postgresql.xa.PGXADataSource"
        val xaProperties = Properties()
        xaProperties.setProperty("user", username)
        xaProperties.setProperty("password", pwd)
        xaProperties.setProperty("serverName", server)
        xaProperties.setProperty("portNumber", port)
        xaProperties.setProperty("databaseName", dbName)
        dataSource.xaProperties = xaProperties
        dataSource.setPoolSize(10)
        return dataSource
    }

    @Bean
    fun salesEntityManager(
        @Value("\${datasource.sales.database}") dbName: String,
        @Value("\${datasource.sales.username}") username: String,
        @Value("\${datasource.sales.password}") pwd: String,
        @Value("\${datasource.sales.server}") server: String,
        @Value("\${datasource.sales.port}") port: String
    ): EntityManagerFactory {
        val vendorAdapter = HibernateJpaVendorAdapter()
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.chomik.storage")
        factory.dataSource = salesDataSource(dbName, username, pwd, server, port)
        val jpaProperties = Properties()
        jpaProperties["hibernate.dialect"] = "org.hibernate.dialect.DerbyDialect"
        jpaProperties["hibernate.current_session_context_class"] = "jta"
        jpaProperties["javax.persistence.transactionType"] = "jta"
        jpaProperties["hibernate.transaction.manager_lookup_class"] =
            "com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup"
        jpaProperties["hibernate.hbm2ddl.auto"] = "none"
        factory.setJpaProperties(jpaProperties)
        factory.afterPropertiesSet()
        return factory.getObject() as EntityManagerFactory
    }
}
