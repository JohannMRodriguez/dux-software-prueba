package com.dux.equipos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEquipoDto {
    private String nombre;
    private String liga;
    private String pais;
}
