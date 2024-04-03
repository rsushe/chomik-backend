package com.chomik.storage.client

import com.chomik.storage.client.dto.SaveSneakersRequest
import com.chomik.storage.client.dto.SneakersDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

private const val SNEAKERS_URL_PREFIX = "api/v1/sneakers"

class SneakersClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun getAllSneakers(): ResponseEntity<List<SneakersDto>> {
        val uri = createBuilder(SNEAKERS_URL_PREFIX)

        return restTemplate.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<SneakersDto>>() {}
        )
    }

    fun getSneakersById(id: String): ResponseEntity<SneakersDto> {
        val uri = createBuilder("${SNEAKERS_URL_PREFIX}/${id}")

        return restTemplate.getForEntity(uri.toUriString(), SneakersDto::class.java)
    }

    fun createSneakers(request: SaveSneakersRequest): ResponseEntity<SneakersDto> {
        val uri = createBuilder(SNEAKERS_URL_PREFIX)

        return restTemplate.postForEntity(uri.toUriString(), request, SneakersDto::class.java)
    }

    fun updateSneakers(id: String, request: SaveSneakersRequest): ResponseEntity<SneakersDto> {
        val uri = createBuilder("${SNEAKERS_URL_PREFIX}/${id}")

        return restTemplate.postForEntity(uri.toUriString(), request, SneakersDto::class.java)
    }

    fun deleteSneakers(id: String): ResponseEntity<Void> {
        val uri = createBuilder("${SNEAKERS_URL_PREFIX}/${id}")

        return restTemplate.exchange(uri.toUriString(), HttpMethod.DELETE, null, Void::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
