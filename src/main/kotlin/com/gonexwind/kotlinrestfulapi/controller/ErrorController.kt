package com.gonexwind.kotlinrestfulapi.controller

import com.gonexwind.kotlinrestfulapi.error.NotFoundException
import com.gonexwind.kotlinrestfulapi.error.UnauthorizedException
import com.gonexwind.kotlinrestfulapi.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> =
        WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(): WebResponse<String> = WebResponse(
        code = 404,
        status = "NOT FOUND",
        data = "not found"
    )

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorized(): WebResponse<String> = WebResponse(
        code = 401,
        status = "UNAUTHORIZED",
        data = "Please put your X-API-KEY"
    )
}