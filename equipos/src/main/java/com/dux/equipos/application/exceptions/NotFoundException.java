package com.dux.equipos.application.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends RuntimeException {
    private final String mensaje;
    public NotFoundException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
}
