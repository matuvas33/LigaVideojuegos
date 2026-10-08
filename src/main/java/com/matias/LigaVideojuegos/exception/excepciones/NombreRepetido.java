package com.matias.LigaVideojuegos.exception.excepciones;

public class NombreRepetido extends RuntimeException{
    public NombreRepetido (String mensaje){
        super(mensaje);
    }
}
