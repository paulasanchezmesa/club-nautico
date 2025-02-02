package com.paulasanchez.club_nautico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulasanchez.club_nautico.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

//  Optional<Persona> findPersonaByEmail(String email);

  Optional<Persona> findByDni(String dni);

  boolean existsByDni(String dni);
}
