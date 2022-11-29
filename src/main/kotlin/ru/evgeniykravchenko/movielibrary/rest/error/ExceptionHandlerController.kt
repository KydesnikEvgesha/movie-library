package ru.evgeniykravchenko.movielibrary.rest.error

import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.time.LocalDateTime
import java.util.NoSuchElementException

@ControllerAdvice
class ExceptionHandlerController : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException, request: WebRequest) =
        handleException(ex, request, status = HttpStatus.NOT_FOUND)

    private fun handleException(
        ex: Exception,
        request: WebRequest,
        status: HttpStatus,
        customMessage: String? = null,
        data: List<Data>? = null,
        printStackTrace: Boolean = false
    ): ResponseEntity<Any> {
        if (printStackTrace) logger.error(ex.message, ex) else logger.error(ex.message)

        return ResponseEntity.status(status).body(
            ErrorMessage(
                timestamp = LocalDateTime.now().toString(),
                status = status.value(),
                error = status.reasonPhrase,
                message = customMessage ?: ex.message.orEmpty(),
                path = (request as ServletWebRequest).request.servletPath,
                data = data
            )
        )
    }
}