package com.dux.equipos.services.implementation;

import com.dux.equipos.application.exceptions.NotFoundException;
import com.dux.equipos.dto.EquipoDto;
import com.dux.equipos.dto.NotFoundDto;
import com.dux.equipos.entities.Equipo;
import com.dux.equipos.repositories.EquiposRepository;
import com.dux.equipos.services.IEquiposService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.dux.equipos.utils.Functions.*;

@Service
@RequiredArgsConstructor
public class EquiposService implements IEquiposService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EquiposRepository repository;

    @Override
    public EquipoDto obtenerEquipo(Long id) {

        var equipo = repository.findById(id);

        if (equipo.isEmpty()) { throw new NotFoundException("Equipo no encontrado"); }
        return objectMapper.convertValue(equipo.get(), EquipoDto.class);
    }

    @Override
    public List<EquipoDto> obtenerEquipoPorNombre(String nombre) {

        var equipos = obtenerEquipos();
        var equiposBuscados = Arrays.stream(nombre.split(",")).toList();
        var response = new ArrayList<EquipoDto>();

        equipos.forEach(each -> {
            if (!StringUtils.isEmpty(checkIfElementIsInList(equiposBuscados, each.getNombre()))) {
                response.add(each);
            }
        });

        return response;
    }

    @Override
    public List<EquipoDto> obtenerEquipos() {

        var equipos = repository.findAll();

        if (equipos.isEmpty()) { throw new NotFoundException("No existen equipos registrados"); }

        return equipos.stream()
                .map(equipo -> objectMapper.convertValue(equipo, EquipoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDto crearEquipo(EquipoDto equipo) {

        var equipoCrated = repository.save(objectMapper.convertValue(equipo, Equipo.class));

        return objectMapper.convertValue(equipoCrated, EquipoDto.class);
    }

    @Override
    public EquipoDto actualizarEquipo(Long id, EquipoDto equipo) {

        obtenerEquipo(id);

        equipo.setId(id);

        return crearEquipo(equipo);
    }

    @Override
    public void eliminarEquipo(Long id) {

        obtenerEquipo(id);
        repository.deleteById(id);
    }
}
