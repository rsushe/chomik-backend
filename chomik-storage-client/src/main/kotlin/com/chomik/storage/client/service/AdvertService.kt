package com.chomik.storage.client.service

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class AdvertService(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun getAllAdverts(): ResponseEntity<List<String>> {
        val uri = createBuilder("api/v1/advert")

        return restTemplate.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<String>>() {}
        )
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
