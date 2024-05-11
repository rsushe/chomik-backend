package com.chomik.storage.service

import com.chomik.storage.client.dto.AdvertDto
import com.chomik.storage.client.dto.SaveAdvertRequest
import com.chomik.storage.client.dto.UpdateSneakersCountRequest
import com.chomik.storage.domain.Advert
import com.chomik.storage.extension.toDto
import com.chomik.storage.repository.storage.AdvertRepository
import com.chomik.storage.service.mapper.AdvertMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class AdvertService(private val advertRepository: AdvertRepository, private val advertMapper: AdvertMapper) {

    fun getAllAdverts(): List<Advert> {
        return advertRepository.findAll()
    }

    fun getAdvertById(id: String): Advert? {
        return advertRepository.findById(id).orElse(null)
    }

    fun getAdvertsBySellerId(sellerId: String): List<Advert> {
        return advertRepository.findBySellerId(sellerId)
    }

    @Transactional
    fun createAdvert(request: SaveAdvertRequest): Advert {
        val advert = advertMapper.toAdvert(request)

        return advertRepository.save(advert)
    }

    @Transactional
    fun updateAdvert(id: String, updateRequest: SaveAdvertRequest): Advert? {
        val existingAdvert = advertRepository.findById(id)
        if (existingAdvert.isEmpty) return null
        val updatedAdvert = advertMapper.toAdvert(updateRequest)
        return advertRepository.save(updatedAdvert)
    }

    @Transactional
    fun updateSneakersCount(advertId: String, updateSneakersCount: UpdateSneakersCountRequest): AdvertDto {
        val advert: Advert = advertRepository.findById(advertId)
            .orElseThrow { IllegalArgumentException("No advert found with id $advertId") }

        val updatedAdvert = advert.copy(sneakerCount = advert.sneakerCount.minus(updateSneakersCount.sneakersCount))
        return advertRepository.save(updatedAdvert).toDto()
    }

    @Transactional
    fun deleteAdvert(id: String) {
        advertRepository.deleteById(id)
    }
}