package com.kashbug.kashbugbackend.error

import com.kashbug.kashbugbackend.application.data.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(value = [NullPointerException::class])
    fun exceptionHandler(exception: NullPointerException) : ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                "",
                "",
                ""
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}