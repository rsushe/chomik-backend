package com.chomik.storage.client.service

import com.chomik.storage.client.dto.SaveAdvertRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class AdvertClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun getAllAdverts(): ResponseEntity<String> {
        val uri = createBuilder("api/v1/advert")

        return restTemplate.getForEntity(uri.toUriString(), String::class.java)
    }

    fun getAdvertById(id: String): ResponseEntity<String> {
        val uri = createBuilder("api/v1/advert/${id}")

        return restTemplate.getForEntity(uri.toUriString(), String::class.java)
    }

    fun getAdvertsBySellerId(id: String): ResponseEntity<String> {
        val uri = createBuilder("api/v1/advert/seller/${id}")

        return restTemplate.getForEntity(uri.toUriString(), String::class.java)
    }

    fun createAdvert(request: SaveAdvertRequest): ResponseEntity<String> {
        val uri = createBuilder("api/v1/advert")

        return restTemplate.postForEntity(uri.toUriString(), request, String::class.java)
    }

    fun updateAdvert(id: String, updateRequest: SaveAdvertRequest): ResponseEntity<String> {
        val uri = createBuilder("api/v1/advert/${id}")

        return restTemplate.postForEntity(uri.toUriString(), updateRequest, String::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
