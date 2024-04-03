package com.chomik.storage.controller

import com.chomik.storage.client.dto.SaveSneakersRequest
import com.chomik.storage.client.dto.SneakersDto
import com.chomik.storage.extension.toDto
import com.chomik.storage.service.SneakersService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
class SneakersController(private val sneakersService: SneakersService) {

    @GetMapping
    fun getAllSneakers(): ResponseEntity<List<SneakersDto>> {
        return ResponseEntity.ok(sneakersService.getAllSneakers().map { it.toDto() })
    }

    @GetMapping("/{id}")
    fun getSneakersById(@PathVariable id: String): ResponseEntity<SneakersDto> {
        val sneakers = sneakersService.getSneakersById(id)
        return sneakers?.let { ResponseEntity.ok(it.toDto()) } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createSneakers(@Valid @RequestBody request: SaveSneakersRequest): ResponseEntity<SneakersDto> {
        val createdSneakers = sneakersService.createSneakers(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSneakers.toDto())
    }

    @PutMapping("/{id}")
    fun updateSneakers(
        @PathVariable id: String,
        @Valid @RequestBody updatedSneakers: SaveSneakersRequest
    ): ResponseEntity<SneakersDto> {
        val updatedSneakersResult = sneakersService.updateSneakers(id, updatedSneakers)
        return updatedSneakersResult?.let { ResponseEntity.ok(it.toDto()) } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteSneakers(@PathVariable id: String): ResponseEntity<Void> {
        sneakersService.deleteSneakers(id)
        return ResponseEntity.noContent().build()
    }
}
