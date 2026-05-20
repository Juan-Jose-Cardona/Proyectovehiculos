package co.edu.usbcali.vehiculosnotificacion.controller;

//integra el dto para estructura de los mensajes
import co.edu.usbcali.vehiculosnotificacion.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestAdviceController {


    //errores de validación
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception, HttpServletRequest request) {

        //crea respuesta de error
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI()
        );

        //retorna error
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    //cuando no encuentra recursos
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException exception, HttpServletRequest request) {

        //crea respuesta de error
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI()
        );

        //retorna error
        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    //maneja errores de base de datos
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest request) {

        //crea respuesta de errore
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                "Hay un error en los datos",
                request.getRequestURI()
        );

        //manda error corto
        return new ResponseEntity<>(
                response,
                HttpStatus.CONFLICT
        );
    }

}
