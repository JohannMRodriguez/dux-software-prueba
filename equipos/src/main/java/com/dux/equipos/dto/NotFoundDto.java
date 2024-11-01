package com.dux.equipos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotFoundDto {
    private String message;
    private int codigo;
}
