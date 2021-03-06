package com.jaewoo.cloud.api.error

import org.springframework.http.HttpStatus
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZonedDateTime

class ErrorResponse(
    val path: String,
    val httpStatus: HttpStatus,
    val message: String
) {
    val timestamp: LocalDateTime = LocalDateTime.now()
}