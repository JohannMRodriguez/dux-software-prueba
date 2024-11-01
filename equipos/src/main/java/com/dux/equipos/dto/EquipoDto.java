package com.dux.equipos.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class EquipoDto {
    @Nullable
    private Long id;
    private String nombre;
    private String liga;
    private String pais;
}
