package com.chomik.storage.client

import com.chomik.storage.client.dto.AdvertDto
import com.chomik.storage.client.dto.SaveAdvertRequest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

private const val ADVERT_URL_PREFIX = "api/v1/advert"

class AdvertClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun getAllAdverts(): ResponseEntity<List<AdvertDto>> {
        val uri = createBuilder(ADVERT_URL_PREFIX)

        return restTemplate.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<AdvertDto>>() {}
        )
    }

    fun getAdvertById(id: String): ResponseEntity<AdvertDto> {
        val uri = createBuilder("${ADVERT_URL_PREFIX}/${id}")

        return restTemplate.getForEntity(uri.toUriString(), AdvertDto::class.java)
    }

    fun getAdvertsBySellerId(id: String): ResponseEntity<AdvertDto> {
        val uri = createBuilder("${ADVERT_URL_PREFIX}/seller/${id}")

        return restTemplate.getForEntity(uri.toUriString(), AdvertDto::class.java)
    }

    fun createAdvert(request: SaveAdvertRequest): ResponseEntity<AdvertDto> {
        val uri = createBuilder(ADVERT_URL_PREFIX)

        return restTemplate.postForEntity(uri.toUriString(), request, AdvertDto::class.java)
    }

    fun updateAdvert(id: String, updateRequest: SaveAdvertRequest): ResponseEntity<AdvertDto> {
        val uri = createBuilder("${ADVERT_URL_PREFIX}/${id}")

        return restTemplate.postForEntity(uri.toUriString(), updateRequest, AdvertDto::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
