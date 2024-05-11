package com.chomik.core.gateway.controller.storage

import com.chomik.core.gateway.domain.UserAuthority.Companion.SNEAKERS_AUTHORITY_NAME
import com.chomik.storage.client.SneakersClient
import com.chomik.storage.client.dto.SaveSneakersRequest
import com.chomik.storage.client.dto.SneakersDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sneakers")
class SneakersController(private val sneakersClient: SneakersClient) {
    @GetMapping
    fun getAllSneakers(): ResponseEntity<List<SneakersDto>> = sneakersClient.getAllSneakers()

    @GetMapping("/{id}")
    fun getSneakersById(@PathVariable id: String): ResponseEntity<SneakersDto> = sneakersClient.getSneakersById(id)

    @PostMapping
    @PreAuthorize("hasAuthority('$SNEAKERS_AUTHORITY_NAME')")
    fun createSneakers(@Valid @RequestBody request: SaveSneakersRequest): ResponseEntity<SneakersDto> =
        sneakersClient.createSneakers(request)

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('$SNEAKERS_AUTHORITY_NAME')")
    fun updateSneakers(
        @PathVariable id: String,
        @Valid @RequestBody updatedSneakers: SaveSneakersRequest
    ): ResponseEntity<SneakersDto> = sneakersClient.updateSneakers(id, updatedSneakers)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('$SNEAKERS_AUTHORITY_NAME')")
    fun deleteSneakers(@PathVariable id: String): ResponseEntity<Void> = sneakersClient.deleteSneakers(id)
}
