package com.dux.equipos.application.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {}
}
