package com.chomik.storage.client

import com.chomik.storage.client.dto.AdvertDto
import com.chomik.storage.client.dto.SaveAdvertRequest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class AdvertClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun getAllAdverts(): ResponseEntity<List<AdvertDto>> {
        val uri = createBuilder("api/v1/advert")

        return restTemplate.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<AdvertDto>>() {}
        )
    }

    fun getAdvertById(id: String): ResponseEntity<AdvertDto> {
        val uri = createBuilder("api/v1/advert/${id}")

        return restTemplate.getForEntity(uri.toUriString(), AdvertDto::class.java)
    }

    fun getAdvertsBySellerId(id: String): ResponseEntity<AdvertDto> {
        val uri = createBuilder("api/v1/advert/seller/${id}")

        return restTemplate.getForEntity(uri.toUriString(), AdvertDto::class.java)
    }

    fun createAdvert(request: SaveAdvertRequest): ResponseEntity<AdvertDto> {
        val uri = createBuilder("api/v1/advert")

        return restTemplate.postForEntity(uri.toUriString(), request, AdvertDto::class.java)
    }

    fun updateAdvert(id: String, updateRequest: SaveAdvertRequest): ResponseEntity<AdvertDto> {
        val uri = createBuilder("api/v1/advert/${id}")

        return restTemplate.postForEntity(uri.toUriString(), updateRequest, AdvertDto::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
