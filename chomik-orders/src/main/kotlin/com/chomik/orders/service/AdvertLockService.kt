package com.chomik.orders.service

import com.chomik.orders.domain.AdvertLock
import com.chomik.orders.repository.AdvertLockRepository
import com.chomik.orders.service.dto.CreateOrderRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdvertLockService(private val advertLockRepository: AdvertLockRepository) {
    @Transactional
    fun save(advertLock: AdvertLock): AdvertLock = advertLockRepository.save(advertLock)

    @Transactional
    fun save(createOrderRequest: CreateOrderRequest): AdvertLock = advertLockRepository.save(
        AdvertLock(
            userId = createOrderRequest.buyerId,
            advertId = createOrderRequest.advertId,
            active = true,
            lockedCount = createOrderRequest.sneakerCount
        )
    )
}
