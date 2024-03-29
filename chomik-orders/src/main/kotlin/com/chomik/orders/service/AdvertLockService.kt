package com.chomik.orders.service

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.domain.AdvertLock
import com.chomik.orders.repository.AdvertLockRepository
import com.chomik.orders.service.dto.CreateOrderRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class AdvertLockService(
    private val advertLockRepository: AdvertLockRepository,
    @Value("\${lock.delta}") private val deltaInSeconds: Long
) {

    @Transactional
<<<<<<< HEAD
    fun save(advertLock: AdvertLock): AdvertLock = advertLockRepository.save(advertLock)
=======
    fun save(advertLock: AdvertLock) = advertLockRepository.save(advertLock)
>>>>>>> 262d10f (CHOMIK-18 joba)

    @Transactional
    fun save(createOrderRequest: CreateOrderRequest): AdvertLock = advertLockRepository.save(
        AdvertLock(
            userId = createOrderRequest.buyerId,
            advertId = createOrderRequest.advertId,
            active = true,
            lockedCount = createOrderRequest.sneakerCount
        )
    )
<<<<<<< HEAD
=======

    @Transactional
    fun deactivateExpiredAdvertLocks() {

        val thresholdTime = Instant.now().minusSeconds(deltaInSeconds)
        advertLockRepository.findAllByLockTimeBeforeAndActiveIsTrue(thresholdTime).forEach {
            it.active = false
            advertLockRepository.save(it)
        }
    }
>>>>>>> 262d10f (CHOMIK-18 joba)
}
