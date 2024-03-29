package com.chomik.core.gateway.config

import com.chomik.storage.client.service.AdvertService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class ApplicationConfiguration {
    @Bean
    fun advertService(@Value("\${chomik.storage.url}") storageUrl: String) = AdvertService(storageUrl)
}
