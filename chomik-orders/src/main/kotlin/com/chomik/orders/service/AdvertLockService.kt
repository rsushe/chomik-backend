package com.chomik.orders.service

import com.chomik.orders.domain.AdvertLock
import com.chomik.orders.repository.AdvertLockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class AdvertLockService(
    private val advertLockRepository: AdvertLockRepository,
) {

    @Transactional
    fun save(advertLock: AdvertLock): AdvertLock = advertLockRepository.save(advertLock)

    @Transactional
    fun deactivateExpiredAdvertLocks(lockTimeoutInSeconds: Long): List<AdvertLock> {
        val thresholdTime = Instant.now().minusSeconds(lockTimeoutInSeconds)
        return advertLockRepository.dropAllLockOlderThan(thresholdTime)
    }
}
