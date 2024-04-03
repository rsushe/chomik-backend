package com.chomik.orders.exception.handler

import com.chomik.orders.exception.InabilityLockingOrderException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleException(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(InabilityLockingOrderException::class)
    fun handleInabilityLockingException(e: InabilityLockingOrderException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Couldn't lock sneakers: ${e.message}")
    }
}
