package com.chomik.storage.service

import com.chomik.storage.client.dto.SaveSneakersRequest
import com.chomik.storage.domain.Sneakers
import com.chomik.storage.repository.storage.SneakersRepository
import com.chomik.storage.service.mapper.SneakersMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SneakersService(private val sneakersRepository: SneakersRepository, private val sneakersMapper: SneakersMapper) {

    fun getAllSneakers(): List<Sneakers> {
        return sneakersRepository.findAll()
    }

    fun getSneakersById(id: String): Sneakers? {
        return sneakersRepository.findById(id).orElse(null)
    }

    @Transactional
    fun createSneakers(request: SaveSneakersRequest): Sneakers {
        val sneakers = sneakersMapper.toSneakers(request)
        return sneakersRepository.save(sneakers)
    }

    @Transactional
    fun updateSneakers(id: String, updateRequest: SaveSneakersRequest): Sneakers? {
        val existingSneakers = sneakersRepository.findById(id)
        if (existingSneakers.isEmpty) return null
        val newSneakers = sneakersMapper.toSneakers(updateRequest)
        return sneakersRepository.save(newSneakers)
    }

    @Transactional
    fun deleteSneakers(id: String) {
        sneakersRepository.deleteById(id)
    }
}