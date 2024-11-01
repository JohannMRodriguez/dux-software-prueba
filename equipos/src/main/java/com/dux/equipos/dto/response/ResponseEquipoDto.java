package com.dux.equipos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEquipoDto {
    private Long id;
    private String nombre;
    private String liga;
    private String pais;
}
