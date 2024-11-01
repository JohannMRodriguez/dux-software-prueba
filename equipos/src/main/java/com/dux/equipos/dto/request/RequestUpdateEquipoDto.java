package com.dux.equipos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateEquipoDto {
    private Long id;
    private String nombre;
    private String liga;
    private String pais;
}
