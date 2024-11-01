package com.dux.equipos.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ResponseEquiposDto {
    private List<ResponseEquipoDto> equipos;
}
