package com.chomik.storage.controller

import com.chomik.storage.client.dto.SaveAdvertRequest
import com.chomik.storage.domain.Advert
import com.chomik.storage.service.AdvertService
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
@RequestMapping("/api/v1/advert")
class AdvertController(private val advertService: AdvertService) {

    @GetMapping
    fun getAllAdverts(): ResponseEntity<List<Advert>> {
        val adverts = advertService.getAllAdverts()
        return ResponseEntity.ok(adverts)
    }

    @GetMapping("/{id}")
    fun getAdvertById(@PathVariable id: String): ResponseEntity<Advert> {
        val advert = advertService.getAdvertById(id)
        return advert?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/seller/{sellerId}")
    fun getAdvertsBySellerId(@PathVariable sellerId: String): ResponseEntity<List<Advert>> {
        val adverts = advertService.getAdvertsBySellerId(sellerId)
        return ResponseEntity.ok(adverts)
    }

    @PostMapping
    fun createAdvert(@Valid @RequestBody request: SaveAdvertRequest): ResponseEntity<Advert> {
        val createdAdvert = advertService.createAdvert(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdvert)
    }

    @PutMapping("/{id}")
    fun updateAdvert(@PathVariable id: String, @Valid @RequestBody updateRequest: SaveAdvertRequest): ResponseEntity<Advert> {
        val updatedAdvert = advertService.updateAdvert(id, updateRequest)
        return if (updatedAdvert != null) {
            ResponseEntity.ok(updatedAdvert)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAdvert(@PathVariable id: String): ResponseEntity<Void> {
        advertService.deleteAdvert(id)
        return ResponseEntity.noContent().build()
    }
}
