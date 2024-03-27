package com.chomik.storage.service

import com.chomik.storage.domain.Sneakers
import com.chomik.storage.repository.SneakersRepository
import com.chomik.storage.service.dto.SaveSneakersRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SneakersService @Autowired constructor(private val sneakersRepository: SneakersRepository) {

    fun getAllSneakers(): List<Sneakers> {
        return sneakersRepository.findAll()
    }

    fun getSneakersById(id: String): Sneakers? {
        return sneakersRepository.findById(id).orElse(null)
    }

    fun createSneakers(request: SaveSneakersRequest): Sneakers {
        val sneakers = Sneakers(
            model = request.model!!,
            brand = request.brand!!,
            size = request.size!!,
            color = request.color,
            condition = request.condition!!
        )

        return sneakersRepository.save(sneakers)
    }

    fun updateSneakers(id: String, updateRequest: SaveSneakersRequest): Sneakers? {
        val existingSneakers = sneakersRepository.findById(id)
        if (existingSneakers.isEmpty) return null
        val newSneakers = Sneakers(
            id = id,
            model = updateRequest.model!!,
            brand = updateRequest.brand!!,
            size = updateRequest.size!!,
            color = updateRequest.color,
            condition = updateRequest.condition!!
        )
        return sneakersRepository.save(newSneakers)
    }

    fun deleteSneakers(id: String) {
        sneakersRepository.deleteById(id)
    }
}