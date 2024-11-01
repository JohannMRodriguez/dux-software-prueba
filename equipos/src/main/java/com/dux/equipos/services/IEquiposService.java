package com.dux.equipos.services;

import com.dux.equipos.dto.EquipoDto;

import java.util.List;

public interface IEquiposService {

    EquipoDto obtenerEquipo(Long id);
    List<EquipoDto> obtenerEquipoPorNombre(String nombre);
    List<EquipoDto> obtenerEquipos();
    EquipoDto crearEquipo(EquipoDto equipo);
    EquipoDto actualizarEquipo(Long id, EquipoDto request);
    void eliminarEquipo(Long id);
}
