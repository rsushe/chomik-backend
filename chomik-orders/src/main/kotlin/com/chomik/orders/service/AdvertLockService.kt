package com.chomik.orders.service

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.domain.AdvertLock
import com.chomik.orders.repository.AdvertLockRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class AdvertLockService(
    private val advertLockRepository: AdvertLockRepository,
    @Value("\${lock.delta}") private val deltaInSeconds: Long,
) {

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

    @Transactional
    fun deactivateExpiredAdvertLocks(): List<AdvertLock> {
        val thresholdTime = Instant.now().minusSeconds(deltaInSeconds)
        return advertLockRepository.dropAllLockOlderThan(thresholdTime)
    }
}
