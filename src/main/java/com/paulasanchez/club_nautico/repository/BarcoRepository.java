package com.paulasanchez.club_nautico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulasanchez.club_nautico.entity.Barco;

public interface BarcoRepository extends JpaRepository<Barco,String>{

	boolean existsByMatricula(String matricula);

	Optional<Barco> findByMatricula(String matricula);

}
