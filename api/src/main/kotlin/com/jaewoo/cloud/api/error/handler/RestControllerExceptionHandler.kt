package com.jaewoo.cloud.api.error.handler

import com.jaewoo.cloud.api.error.ErrorResponse
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.api.error.exception.NotfoundException
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotfoundException::class)
    fun handleNotFoundException(request: ServerHttpRequest, ex: Exception): ErrorResponse {
        return createErrorResponse(HttpStatus.NOT_FOUND, request, ex)
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException::class)
    fun handleInvalidInputException(request: ServerHttpRequest, ex: Exception): ErrorResponse {
        return createErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, request, ex)
    }

    fun createErrorResponse(
        httpStatus: HttpStatus,
        request: ServerHttpRequest,
        ex: Exception
    ) : ErrorResponse {
        return ErrorResponse(
            httpStatus = httpStatus,
            path = request.path.pathWithinApplication().value(),
            message = ex.message ?: ""
        )
    }
}