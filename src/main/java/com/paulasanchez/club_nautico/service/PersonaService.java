package com.paulasanchez.club_nautico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paulasanchez.club_nautico.entity.Persona;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;


@Service
public interface PersonaService {

	List<Persona> findAllPersonas();
    Persona findPersonaById(String dni) throws NotFoundException;
    Persona savePersona(Persona persona) throws DuplicateException;
    Persona updatePersona(String dni, Persona persona)throws NotFoundException;
    Persona deletePersona(String id)throws NotFoundException;

}
