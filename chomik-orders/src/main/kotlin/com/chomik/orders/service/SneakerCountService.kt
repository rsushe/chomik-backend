package com.chomik.orders.service

import com.chomik.orders.domain.SneakerCount
import com.chomik.orders.repository.SneakerCountRepository
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import kotlin.jvm.optionals.getOrElse

@Service
class SneakerCountService(private val sneakerCountRepository: SneakerCountRepository) {
    fun save(sneakerCount: SneakerCount): SneakerCount = sneakerCountRepository.save(sneakerCount)

    fun updateCount(sneakerCount: SneakerCount) =
        sneakerCountRepository.updateCount(sneakerCount.sneakerId, sneakerCount.count)

    fun getSneakerCount(sneakerId: String) = sneakerCountRepository.findById(sneakerId).getOrElse {
        throw NoSuchElementException("Sneaker count with sneakerId $sneakerId doesn't exists")
    }.count
}
