package com.dux.equipos.resources;

import com.dux.equipos.application.swagger.SwaggerApiConfig;
import com.dux.equipos.dto.request.RequestEquipoDto;
import com.dux.equipos.dto.response.ResponseEquipoDto;
import com.dux.equipos.services.IEquiposService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dux.equipos.utils.Constantes.*;

@RestController
@RequestMapping(BASE_PATH)
@Tag(name = SwaggerApiConfig.API_TAG, description = SwaggerApiConfig.API_DESCRIPTION)
public class EquiposResource {

    @Autowired
    private IEquiposService service;


    @GetMapping
    @Operation(summary = OBTENER_EQUIPOS_TAG)
    public ResponseEntity<List<ResponseEquipoDto>> obtenerEquipos() {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.obtenerEquipos());
    }

    @GetMapping("/{id}")
    @Operation(summary = OBTENER_EQUIPO_TAG)
    public ResponseEntity<ResponseEquipoDto> obtenerEquipo(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.obtenerEquipo(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = OBTENER_EQUIPOS_NOMBRE_TAG)
    public ResponseEntity<List<ResponseEquipoDto>> obtenerEquipoPorNombre(@RequestParam(name = "nombre") String nombre) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.obtenerEquipoPorNombre(nombre));
    }

    @PostMapping
    @Operation(summary = CREAR_EQUIPO_TAG)
    public ResponseEntity<ResponseEquipoDto> crearEquipo(@RequestBody RequestEquipoDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearEquipo(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = ACTUALIZAR_EQUIPO_TAG)
    public ResponseEntity<ResponseEquipoDto> actualizarEquipo(@PathVariable Long id, @RequestBody RequestEquipoDto request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.actualizarEquipo(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = ELIMINAR_EQUIPO_TAG)
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {

        service.eliminarEquipo(id);
        return ResponseEntity.noContent().build();
    }
}
