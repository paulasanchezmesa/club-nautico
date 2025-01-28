package com.paulasanchez.club_nautico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulasanchez.club_nautico.entity.Barco;
import com.paulasanchez.club_nautico.entity.Miembro;
import com.paulasanchez.club_nautico.entity.Persona;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.repository.BarcoRepository;
import com.paulasanchez.club_nautico.repository.MiembroRepository;
import com.paulasanchez.club_nautico.repository.PersonaRepository;

@Service
public class MiembroServiceImpl implements MiembroService {

	@Autowired
	private BarcoRepository barcoRepository;

    @Autowired
    private MiembroRepository miembroRepository;

    @Autowired
    private PersonaRepository personaRepository;

	@Override
	public List<Miembro> findAllMiembros() {
        return miembroRepository.findAll();

	}

	@Override
	public List<Barco> findAllMiembroBarcos(Integer id) throws NotFoundException {
		 Miembro miembro = findMiembroById(id);
	        return miembro.getBarcos();
	}

	@Override
	public Miembro findMiembroById(int id) throws NotFoundException {
		return miembroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Miembro con ID " + id + " no encontrado."));
	}

	@Override
	public Miembro saveMiembro(Miembro miembro) throws DuplicateException, NotFoundException {
		 Persona persona = miembro.getPersona();
		 System.out.println(persona);

	        if (persona == null || persona.getDni() == null) {
	            throw new NotFoundException("La persona asociada no puede ser nula y debe tener un DNI válido.");
	        }
	        Optional<Persona> existingPersona = personaRepository.findByDni(persona.getDni());
	        if (existingPersona.isEmpty()) {
	            throw new NotFoundException("La persona con DNI " + persona.getDni() + " no existe en el sistema.");
	        }

	        Optional<Miembro> existingMiembro = miembroRepository.findById(miembro.getId());  // Aquí tendrías que buscar por ID o algún atributo único de Miembro
	        if (existingMiembro.isPresent()) {
	            throw new DuplicateException("El miembro con la persona asociada ya existe.");
	        }

	        List<Barco> barcos = miembro.getBarcos();
	        for	(Barco b : barcos) {
	        	b.setPropietario(miembro);
	        }

	        miembro.setPersona(existingPersona.get());
	        return miembroRepository.save(miembro);
	    }

	@Override
	public Miembro updateMiembro(int id, Miembro miembro) throws NotFoundException {
		 Miembro existingMiembro = findMiembroById(id);

	        Persona persona = miembro.getPersona();
	        if (persona == null || persona.getDni() == null) {
	            throw new NotFoundException("La persona asociada no puede ser nula y debe tener un DNI válido.");
	        }

	        Optional<Persona> existingPersona = personaRepository.findByDni(persona.getDni());
	        if (existingPersona.isEmpty()) {
	            throw new NotFoundException("La persona con DNI " + persona.getDni() + " no existe en el sistema.");
	        }

	        existingMiembro.setPersona(existingPersona.get());
	        existingMiembro.setBarcos(miembro.getBarcos());
	        return miembroRepository.save(existingMiembro);
	}

	@Override
	public Miembro deleteMiembro(int id) throws NotFoundException {
		  Miembro miembro = findMiembroById(id);
	        miembroRepository.delete(miembro);
	        return miembro;
	}


}
