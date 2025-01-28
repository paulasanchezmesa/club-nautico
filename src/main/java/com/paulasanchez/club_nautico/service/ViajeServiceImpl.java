package com.paulasanchez.club_nautico.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulasanchez.club_nautico.entity.Barco;
import com.paulasanchez.club_nautico.entity.Persona;
import com.paulasanchez.club_nautico.entity.Viaje;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.repository.BarcoRepository;
import com.paulasanchez.club_nautico.repository.PersonaRepository;
import com.paulasanchez.club_nautico.repository.ViajeRepository;

@Service
public class ViajeServiceImpl implements ViajeService {

	@Autowired
	ViajeRepository viajeRepository;

	@Autowired
	BarcoRepository barcoRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Override
	public List<Viaje> findAllViajes() {
		return viajeRepository.findAll();
	}

	@Override
	public Viaje findViajeById(int id) throws NotFoundException {
		return viajeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("viaje con id " + id + " no encontrado"));

	}

	@Override
	public Viaje saveViaje(Viaje viaje) throws DuplicateException, NotFoundException {
		Barco barco = viaje.getBarco();
		Persona organizador = viaje.getOrganizador();

		if (barco == null || barco.getMatricula() == null) {
            throw new NotFoundException("El barco no puede ser nula y debe tener un DNI válido.");
        }

		Optional<Barco> existingBarco = barcoRepository.findByMatricula(barco.getMatricula());
		if (!existingBarco.isPresent()) {
			throw new NotFoundException("No existe el barco");
		}

		Optional<Persona> existingOrganizador = personaRepository.findByDni(organizador.getDni());
		if (!existingOrganizador.isPresent()) {
			throw new NotFoundException("No existe la persona");
		}

		viaje.setBarco(existingBarco.get());
		viaje.setOrganizador(existingOrganizador.get());


		boolean encontrado = viajeRepository.findAll().stream().anyMatch(s -> {
			if (Objects.nonNull(s.getBarco()) && Objects.nonNull(viaje.getBarco())) {
				return s.getFechaHora().equals(viaje.getFechaHora())
						&& s.getBarco().getMatricula().equals(viaje.getBarco().getMatricula());
			} else {
				return false; // Si uno de los barcos es nulo, considerarlos distintos
			}
		});
		if (!barcoRepository.existsByMatricula(viaje.getBarco().getMatricula())) {
			throw new NotFoundException("No existe barco con matricula: " + viaje.getBarco().getMatricula());
		}
		if (encontrado) {
			throw new DuplicateException("La viaje del barco " + viaje.getBarco().getMatricula() + " y fecha/hora "
					+ viaje.getFechaHora() + " ya está registrada");
		}
		return viajeRepository.save(viaje);

	}

	@Override
	public Viaje updateViaje(Integer id, Viaje viaje) throws NotFoundException {
		
		if(!viajeRepository.existsById(id)){
            throw new NotFoundException("viaje con id " + id + " no encontrado");
        }else {
            Viaje viaje_db = viajeRepository.findById(id).get();
            viaje_db.setFechaHora(viaje.getFechaHora());
            viaje_db.setDescripcion(viaje.getDescripcion());
               
            return viajeRepository.save(viaje_db);
        }
	}

	@Override
	public Viaje deleteViaje(Integer id) throws NotFoundException {
		Viaje viaje = viajeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("viaje no encontrada con ID: " + id));
		viajeRepository.deleteById(id);
		return viaje;
	}

}
