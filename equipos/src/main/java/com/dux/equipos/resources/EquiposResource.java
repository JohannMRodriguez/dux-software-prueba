package com.dux.equipos.resources;

import com.dux.equipos.dto.EquipoDto;
import com.dux.equipos.services.IEquiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dux.equipos.utils.Constantes.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH)
public class EquiposResource {

    @Autowired
    private IEquiposService service;


    @GetMapping
    public ResponseEntity<List<EquipoDto>> obtenerEquipos() {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.obtenerEquipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoDto> obtenerEquipo(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.obtenerEquipo(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EquipoDto>> obtenerEquipoPorNombre(@RequestParam(name = "nombre") String nombre) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.obtenerEquipoPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<EquipoDto> crearEquipo(@RequestBody EquipoDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearEquipo(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoDto> actualizarEquipo(@PathVariable Long id, @RequestBody EquipoDto request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.actualizarEquipo(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {

        service.eliminarEquipo(id);
        return ResponseEntity.noContent().build();
    }
}
