package com.chomik.core.gateway.exception.handler

import com.chomik.core.gateway.exception.UserDuplicateException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.RestClientResponseException

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(
        HttpMessageNotReadableException::class,
        UserDuplicateException::class,
        IllegalArgumentException::class
    )
    fun handleException(e: Exception): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(RestClientResponseException::class)
    fun handleException(e: RestClientResponseException): ResponseEntity<String> {
        return ResponseEntity.status(e.statusCode).body(e.message)
    }
}
