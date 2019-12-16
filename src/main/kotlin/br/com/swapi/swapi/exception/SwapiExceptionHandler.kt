package br.com.swapi.swapi.exception

import br.com.swapi.swapi.data.ValidationError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


/**
 * Classe que intercepta as exceções lançadas pela aplicação
 */
@ControllerAdvice
class SwapiExceptionHandler {

    /**
     * Intercepta a exceção lançada quando há algum erro de validação
     *
     * @param exception MethodArgumentNotValidException
     * @return ResponseEntity<MutableList<Map<String, String>>>
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(exception: MethodArgumentNotValidException): ResponseEntity<MutableList<ValidationError>> {
        val validationErrors = exception.bindingResult.fieldErrors
        val errors = mutableListOf<ValidationError>()

        for (error in validationErrors) {
            errors.add(ValidationError(error.field, error.defaultMessage!!))
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors)
    }

    /**
     * Intercepta exceção lançada quando há algum erro na API
     *
     * @param exception ApiException
     * @return ResponseEntity<Map<String, String>>
     */
    @ExceptionHandler(ApiException::class)
    fun handleApiException(exception: ApiException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to exception.localizedMessage))
    }
}