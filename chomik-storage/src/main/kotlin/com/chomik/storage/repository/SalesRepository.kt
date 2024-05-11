package com.chomik.storage.repository

import com.chomik.storage.domain.Sales
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SalesRepository : JpaRepository<Sales, String> {
}