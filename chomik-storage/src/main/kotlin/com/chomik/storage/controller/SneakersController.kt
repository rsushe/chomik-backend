package com.chomik.storage.controller

import com.chomik.storage.client.dto.SaveSneakersRequest
import com.chomik.storage.domain.Sneakers
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
    fun getAllSneakers(): List<Sneakers> {
        return sneakersService.getAllSneakers()
    }

    @GetMapping("/{id}")
    fun getSneakersById(@PathVariable id: String): ResponseEntity<Sneakers> {
        val sneakers = sneakersService.getSneakersById(id)
        return if (sneakers != null) {
            ResponseEntity.ok(sneakers)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createSneakers(@Valid @RequestBody request: SaveSneakersRequest): ResponseEntity<Sneakers> {
        val createdSneakers = sneakersService.createSneakers(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSneakers)
    }

    @PutMapping("/{id}")
    fun updateSneakers(
        @PathVariable id: String,
        @Valid @RequestBody updatedSneakers: SaveSneakersRequest
    ): ResponseEntity<Sneakers> {
        val updatedSneakersResult = sneakersService.updateSneakers(id, updatedSneakers)
        return if (updatedSneakersResult != null) {
            ResponseEntity.ok(updatedSneakersResult)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSneakers(@PathVariable id: String): ResponseEntity<Void> {
        sneakersService.deleteSneakers(id)
        return ResponseEntity.noContent().build()
    }
}
