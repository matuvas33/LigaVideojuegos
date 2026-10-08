package com.matias.LigaVideojuegos.exception;

import com.matias.LigaVideojuegos.dto.error.MensajeDetalles;
import com.matias.LigaVideojuegos.dto.error.MensajeError;
import com.matias.LigaVideojuegos.exception.excepciones.EquipoNoEncontrado;
import com.matias.LigaVideojuegos.exception.excepciones.LigaNoEncontrada;
import com.matias.LigaVideojuegos.exception.excepciones.NombreRepetido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LigaNoEncontrada.class)
    public ResponseEntity<MensajeError> recursoEncontrado(LigaNoEncontrada ligaNoEncontrada){

        MensajeError errorBody = new MensajeError(
                "404",
                "Not found",
                ligaNoEncontrada.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(errorBody);
    }
    @ExceptionHandler(EquipoNoEncontrado.class)
    public ResponseEntity<MensajeError>recursoNoEncontrado(EquipoNoEncontrado equipoNoEncontrado){
        MensajeError error = new MensajeError(
                "404",
                "Not Found",
                equipoNoEncontrado.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(NombreRepetido.class)
    public ResponseEntity<MensajeError>nombreRepetido(NombreRepetido nombreRepetido){
        MensajeError error = new MensajeError(
                "409",
                "Conflict",
                nombreRepetido.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<MensajeDetalles>filtroInvalido(MethodArgumentTypeMismatchException ex){

        String paramentro = ex.getName();
        String valorIncorrecto = (String) ex.getValue();

        MensajeDetalles error= new MensajeDetalles(
                "400",
                "Bad Request",
                "Filtro o parametro invalido.",
                "El parametro "+paramentro+" recibio el valor "+valorIncorrecto+" el cual no es acpetado.",
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDetalles>validacionInvalida(MethodArgumentNotValidException ex){
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String campo = fieldError != null ? fieldError.getField():"desconocido";
        String mensajeFallo = fieldError !=null ? fieldError.getDefaultMessage():"Datos invalidos";

        MensajeDetalles error = new MensajeDetalles(
                "400",
                "Bad Request",
                "Error en la validación de los datos enviados.",
                "El campo '" + campo + "' tiene el siguiente error: " + mensajeFallo,
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<MensajeDetalles>validarHeader(MissingRequestHeaderException ex){
        String header = ex.getHeaderName();

        MensajeDetalles error = new MensajeDetalles(
                "400",
                "Bad Request",
                "Falta la cabecera obligatoria",
                "La cabecera requerida "+header+" no esta presente",
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }





}
