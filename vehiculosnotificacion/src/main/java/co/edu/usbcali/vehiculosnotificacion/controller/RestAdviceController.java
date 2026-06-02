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

//importaciones para el valid
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestAdviceController {

    //NOTA: Agregar el error tipo 409


    //errores de validación es el error 400
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

    //cuando no encuentra recursos (es el error 404)
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

    //maneja errores de base de datos (es el error 409)
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

    //maneja errores valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {

        //extrae mensajes de validación
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        //crea respuesta de error
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message,
                request.getRequestURI()
        );

        //retorna error validado
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    /*
    //maneja errores por fallar de la nada (es el error 500) general por que ya esta e  runtime usado
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> Exception(RuntimeException exception, HttpServletRequest request) {

        //crea respuesta de error
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI()
        );

        //retorna error 500
        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    */


}
