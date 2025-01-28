package com.paulasanchez.club_nautico.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulasanchez.club_nautico.entity.Persona;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
    PersonaRepository personaRepository;

	@Override
	public List<Persona> findAllPersonas() {
        return personaRepository.findAll();
	}

	@Override
	public Persona findPersonaById(String dni) throws NotFoundException {
		 return personaRepository.findById(dni).orElseThrow(()-> new NotFoundException("persona no encontrado con DNI: " + dni));
	}

	@Override
	public Persona savePersona(Persona persona) throws DuplicateException {
		if (personaRepository.existsById(persona.getDni())) {
            throw new DuplicateException("El persona con dni " + persona.getDni() + " ya esta registrado");
        } else {
            return personaRepository.save(persona);
        }
	}

	@Override
	public Persona updatePersona(String dni, Persona persona) throws NotFoundException {
		if(!personaRepository.existsById(dni)){
            throw new NotFoundException("persona con dni " + dni + " no encontrado");
        }else {
            Persona persona_db = personaRepository.findById(dni).get();

            if (Objects.nonNull(persona.getDni()) && !" ".equalsIgnoreCase(persona.getDni())) {
                persona_db.setDni(persona.getDni());
            }

            if (Objects.nonNull(persona.getNombre()) && !" ".equalsIgnoreCase(persona.getNombre())) {
                persona_db.setNombre(persona.getNombre());
            }

            if (Objects.nonNull(persona.getApellidos()) && !" ".equalsIgnoreCase(persona.getApellidos())) {
                persona_db.setApellidos(persona.getApellidos());
            }
            return personaRepository.save(persona_db);
        }
	}

	@Override
	public Persona deletePersona(String dni) throws NotFoundException {
		Persona persona = personaRepository.findById(dni)
                .orElseThrow(() -> new NotFoundException("persona no encontrado con DNI: " + dni));

        personaRepository.deleteById(dni);
        return persona;
	}



}
