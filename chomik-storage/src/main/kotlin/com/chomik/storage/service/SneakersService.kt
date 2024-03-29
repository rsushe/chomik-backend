package com.chomik.storage.service

import com.chomik.storage.client.dto.SaveSneakersRequest
import com.chomik.storage.domain.Sneakers
import com.chomik.storage.repository.SneakersRepository
import com.chomik.storage.service.mapper.SneakersMapper
import org.springframework.stereotype.Service

@Service
class SneakersService(private val sneakersRepository: SneakersRepository, private val sneakersMapper: SneakersMapper) {

    fun getAllSneakers(): List<Sneakers> {
        return sneakersRepository.findAll()
    }

    fun getSneakersById(id: String): Sneakers? {
        return sneakersRepository.findById(id).orElse(null)
    }

    fun createSneakers(request: SaveSneakersRequest): Sneakers {
        val sneakers = sneakersMapper.toSneakers(request)
        return sneakersRepository.save(sneakers)
    }

    fun updateSneakers(id: String, updateRequest: SaveSneakersRequest): Sneakers? {
        val existingSneakers = sneakersRepository.findById(id)
        if (existingSneakers.isEmpty) return null
        val newSneakers = sneakersMapper.toSneakers(updateRequest)
        return sneakersRepository.save(newSneakers)
    }

    fun deleteSneakers(id: String) {
        sneakersRepository.deleteById(id)
    }
}