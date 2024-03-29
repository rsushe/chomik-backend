package com.chomik.orders.service

import com.chomik.orders.domain.AdvertLock
import com.chomik.orders.repository.AdvertLockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdvertLockService(private val advertLockRepository: AdvertLockRepository) {
    @Transactional
    fun save(advertLock: AdvertLock) = advertLockRepository.save(advertLock)
}
