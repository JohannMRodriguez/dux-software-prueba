package com.dux.equipos.services;

import com.dux.equipos.dto.request.RequestEquipoDto;
import com.dux.equipos.dto.response.ResponseEquipoDto;

import java.util.List;

public interface IEquiposService {

    ResponseEquipoDto obtenerEquipo(Long id);
    List<ResponseEquipoDto> obtenerEquipoPorNombre(String nombre);
    List<ResponseEquipoDto> obtenerEquipos();
    ResponseEquipoDto crearEquipo(RequestEquipoDto equipo);
    ResponseEquipoDto actualizarEquipo(Long id, RequestEquipoDto request);
    void eliminarEquipo(Long id);
}
