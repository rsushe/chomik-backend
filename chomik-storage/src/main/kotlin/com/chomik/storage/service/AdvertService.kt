package com.chomik.storage.service

import com.chomik.storage.client.dto.SaveAdvertRequest
import com.chomik.storage.domain.Advert
import com.chomik.storage.repository.AdvertRepository
import com.chomik.storage.service.mapper.AdvertMapper
import org.springframework.stereotype.Service

@Service
class AdvertService(private val advertRepository: AdvertRepository, private val advertMapper: AdvertMapper)  {

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
        val advert = advertMapper.toAdvert(request);

        return advertRepository.save(advert)
    }

    fun updateAdvert(id: String, updateRequest: SaveAdvertRequest): Advert? {
        val existingAdvert = advertRepository.findById(id)
        if (existingAdvert.isEmpty) return null
        val updatedAdvert = advertMapper.toAdvert(updateRequest)
        return advertRepository.save(updatedAdvert)
    }

    fun deleteAdvert(id: String) {
        advertRepository.deleteById(id)
    }
}