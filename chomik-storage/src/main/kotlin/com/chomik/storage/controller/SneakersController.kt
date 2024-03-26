package com.chomik.storage.controller

import com.chomik.storage.domain.Sneakers
import com.chomik.storage.service.SneakersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/sneakers")
class SneakersController @Autowired constructor(private val sneakersService: SneakersService) {

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
    fun createSneakers(@RequestBody sneakers: Sneakers): ResponseEntity<Sneakers> {
        val createdSneakers = sneakersService.createSneakers(sneakers)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSneakers)
    }

    @PutMapping("/{id}")
    fun updateSneakers(@PathVariable id: String, @RequestBody updatedSneakers: Sneakers): ResponseEntity<Sneakers> {
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
