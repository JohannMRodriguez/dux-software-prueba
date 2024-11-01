package com.dux.equipos.dto.response;

import com.dux.equipos.dto.EquipoDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ResponseEquiposDto {
    private List<EquipoDto> equipos;
}
