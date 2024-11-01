package com.dux.equipos.services.implementation;

import com.dux.equipos.application.exceptions.NotFoundException;
import com.dux.equipos.dto.request.RequestEquipoDto;
import com.dux.equipos.dto.request.RequestUpdateEquipoDto;
import com.dux.equipos.dto.response.ResponseEquipoDto;
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
    public ResponseEquipoDto obtenerEquipo(Long id) {

        var equipo = repository.findById(id);

        if (equipo.isEmpty()) { throw new NotFoundException("Equipo no encontrado"); }
        return objectMapper.convertValue(equipo.get(), ResponseEquipoDto.class);
    }

    @Override
    public List<ResponseEquipoDto> obtenerEquipoPorNombre(String nombre) {

        var equipos = obtenerEquipos();
        var equiposBuscados = Arrays.stream(nombre.split(",")).toList();
        var response = new ArrayList<ResponseEquipoDto>();

        equipos.forEach(each -> {
            if (!StringUtils.isEmpty(checkIfElementIsInList(equiposBuscados, each.getNombre()))) {
                response.add(each);
            }
        });

        return response;
    }

    @Override
    public List<ResponseEquipoDto> obtenerEquipos() {

        var equipos = repository.findAll();

        if (equipos.isEmpty()) { throw new NotFoundException("No existen equipos registrados"); }

        return equipos.stream()
                .map(equipo -> objectMapper.convertValue(equipo, ResponseEquipoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEquipoDto crearEquipo(RequestEquipoDto equipo) {

        var equipoCrated = repository.save(objectMapper.convertValue(equipo, Equipo.class));
        return objectMapper.convertValue(equipoCrated, ResponseEquipoDto.class);
    }

    @Override
    public ResponseEquipoDto actualizarEquipo(Long id, RequestEquipoDto equipo) {

        obtenerEquipo(id);

//        if it doesn't throw the exception it means that the teams already exists, just need to update with save method
        var updateEquipo = new RequestUpdateEquipoDto(
                id, equipo.getNombre(), equipo.getLiga(), equipo.getPais()
        );

        var updatedEquipo = repository.save(objectMapper.convertValue(updateEquipo, Equipo.class));
        return objectMapper.convertValue(updatedEquipo, ResponseEquipoDto.class);
    }

    @Override
    public void eliminarEquipo(Long id) {

        obtenerEquipo(id);
        repository.deleteById(id);
    }
}
