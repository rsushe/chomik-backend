package com.chomik.core.chomikgateway.exception.handler

import com.chomik.core.chomikgateway.exception.UserDuplicateException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class, UserDuplicateException::class)
    fun handleException(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
}
