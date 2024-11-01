package com.dux.equipos.repositories;

import com.dux.equipos.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquiposRepository extends JpaRepository<Equipo, Long> {

//    definimos los metodos que no están incluidos por defecto en JPARepository
    Optional<Equipo> findByNombre(String nombre);
}
