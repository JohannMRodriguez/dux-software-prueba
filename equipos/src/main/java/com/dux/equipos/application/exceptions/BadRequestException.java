package com.dux.equipos.application.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadRequestException extends RuntimeException {
    private final String mensaje;
    public BadRequestException(String mensaje) {
        this.mensaje = mensaje;
    }
}
