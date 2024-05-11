package com.chomik.storage.config

import com.atomikos.jdbc.AtomikosDataSourceBean
import com.chomik.storage.repository.storage.AdvertRepository
import com.chomik.storage.repository.storage.SneakersRepository
import jakarta.persistence.EntityManagerFactory
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackageClasses = [AdvertRepository::class, SneakersRepository::class], entityManagerFactoryRef = "storageEntityManager", transactionManagerRef = "transactionManager")
class StorageDatasourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "datasource.storage.liquibase")
    fun storageLiquibaseProperties(): LiquibaseProperties {
        return LiquibaseProperties()
    }

    @Bean
    fun storageLiquibase(
        @Value("\${datasource.storage.database}") dbName: String,
        @Value("\${datasource.storage.username}") username: String,
        @Value("\${datasource.storage.password}") pwd: String,
        @Value("\${datasource.storage.server}") server: String,
        @Value("\${datasource.storage.port}") port: String): SpringLiquibase? {
        return springLiquibase(storageDataSource(dbName, username, pwd, server, port), storageLiquibaseProperties())
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    fun storageDataSource(
        @Value("\${datasource.storage.database}") dbName: String,
        @Value("\${datasource.storage.username}") username: String,
        @Value("\${datasource.storage.password}") pwd: String,
        @Value("\${datasource.storage.server}") server: String,
        @Value("\${datasource.storage.port}") port: String): AtomikosDataSourceBean {
        val dataSource = AtomikosDataSourceBean()
        dataSource.uniqueResourceName = "db1"
        dataSource.xaDataSourceClassName = "org.postgresql.xa.PGXADataSource"
        val xaProperties = Properties()
        xaProperties.setProperty ( "user" , username );
        xaProperties.setProperty ( "password" , pwd );
        xaProperties.setProperty ( "serverName" , server );
        xaProperties.setProperty ( "portNumber" , port );
        xaProperties.setProperty ( "databaseName" , dbName );
        dataSource.xaProperties = xaProperties
        dataSource.setPoolSize(10)
        return dataSource
    }

    @Bean
    fun storageEntityManager(@Value("\${datasource.storage.database}") dbName: String,
                             @Value("\${datasource.storage.username}") username: String,
                             @Value("\${datasource.storage.password}") pwd: String,
                             @Value("\${datasource.storage.server}") server: String,
                             @Value("\${datasource.storage.port}") port: String): EntityManagerFactory {
        val vendorAdapter = HibernateJpaVendorAdapter()
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.chomik.storage")
        factory.dataSource = storageDataSource(dbName, username, pwd, server, port)
        val jpaProperties = Properties()
        jpaProperties["hibernate.dialect"] = "org.hibernate.dialect.DerbyDialect"
        jpaProperties["hibernate.current_session_context_class"] = "jta"
        jpaProperties["javax.persistence.transactionType"] = "jta"
        jpaProperties["hibernate.transaction.manager_lookup_class"] = "com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup"
        jpaProperties["hibernate.hbm2ddl.auto"] = "create-drop"
        factory.setJpaProperties(jpaProperties)
        factory.afterPropertiesSet()
        return factory.getObject() as EntityManagerFactory
    }

    private fun springLiquibase(dataSource: DataSource, properties: LiquibaseProperties): SpringLiquibase? {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = properties.changeLog
        liquibase.contexts = properties.contexts
        liquibase.defaultSchema = properties.defaultSchema
        liquibase.isDropFirst = properties.isDropFirst
        liquibase.setShouldRun(properties.isEnabled)
        liquibase.setChangeLogParameters(properties.parameters)
        liquibase.setRollbackFile(properties.rollbackFile)
        return liquibase
    }
}
