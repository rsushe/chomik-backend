package com.chomik.orders.service

import com.chomik.orders.domain.SneakerCount
import com.chomik.orders.repository.SneakerCountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.NoSuchElementException
import kotlin.jvm.optionals.getOrElse

@Service
class SneakerCountService(private val sneakerCountRepository: SneakerCountRepository) {
    @Transactional
    fun save(sneakerCount: SneakerCount): SneakerCount = sneakerCountRepository.save(sneakerCount)

    fun updateCount(advertId: String, count: Int) = sneakerCountRepository.updateCount(advertId, count)

    fun getSneakerCount(advertId: String) = sneakerCountRepository.findById(advertId).getOrElse {
        throw NoSuchElementException("Sneaker count with advertId $advertId doesn't exists")
    }.count
}
