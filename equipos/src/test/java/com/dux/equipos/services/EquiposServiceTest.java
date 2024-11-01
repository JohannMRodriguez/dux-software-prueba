package com.dux.equipos.services;

import com.dux.equipos.application.exceptions.NotFoundException;
import com.dux.equipos.dto.response.ResponseEquipoDto;
import com.dux.equipos.entities.Equipo;
import com.dux.equipos.repositories.EquiposRepository;
import com.dux.equipos.services.implementation.EquiposService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EquiposServiceTest {

    @InjectMocks
    private EquiposService service;
    @Mock
    private EquiposRepository repository;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("busco equipo con id valido y retorna OK")
    void obtenerEquipoPorId_Success() {

        Long id = 1L;

        Equipo entityResponse = new Equipo(1L, "nombre", "liga", "pais");
        ResponseEquipoDto expectedResponse = new ResponseEquipoDto(1L, "nombre", "liga", "pais");

        when(repository.findById(anyLong())).thenReturn(Optional.of(entityResponse));
        when(objectMapper.convertValue(entityResponse, ResponseEquipoDto.class)).thenReturn(expectedResponse);

        ResponseEquipoDto response = service.obtenerEquipo(id);

        assertNotNull(response);
        assertEquals(response.getId(), expectedResponse.getId());
    }

    @Test
    @DisplayName("busco equipo con id invalido y retorna ERROR")
    void obtenerEquipoPorId_Error() {

        Long id = 1L;

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.obtenerEquipo(id));
    }
}
