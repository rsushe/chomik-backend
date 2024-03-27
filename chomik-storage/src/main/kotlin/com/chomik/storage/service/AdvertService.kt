package com.chomik.storage.service

import com.chomik.storage.domain.Advert
import com.chomik.storage.repository.AdvertRepository
import com.chomik.storage.service.dto.SaveAdvertRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class AdvertService @Autowired constructor(private val advertRepository: AdvertRepository)  {

    fun getAllAdverts(): List<Advert> {
        return advertRepository.findAll()
    }

    fun getAdvertById(id: String): Advert? {
        return advertRepository.findById(id).orElse(null)
    }

    fun getAdvertsBySellerId(sellerId: String): List<Advert> {
        return advertRepository.findBySellerId(sellerId)
    }

    fun createAdvert(request: SaveAdvertRequest): Advert {
        val advert = Advert(
            sneakerId = request.sneakerId,
            sellerId = request.sellerId,
            status = request.status,
            price = request.price,
            active = request.active
        )

        return advertRepository.save(advert)
    }

    fun updateAdvert(id: String, updateRequest: SaveAdvertRequest): Advert? {
        val existingAdvert = advertRepository.findById(id)
        if (existingAdvert.isEmpty) return null
        val updatedAdvert = Advert(
            id = id,
            sneakerId = updateRequest.sneakerId,
            sellerId = updateRequest.sellerId,
            status = updateRequest.status,
            price = updateRequest.price,
            active = updateRequest.active
        )
        return advertRepository.save(updatedAdvert)
    }

    fun deleteAdvert(id: String) {
        advertRepository.deleteById(id)
    }
}