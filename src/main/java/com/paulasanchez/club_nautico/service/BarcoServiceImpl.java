package com.paulasanchez.club_nautico.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulasanchez.club_nautico.entity.Barco;
import com.paulasanchez.club_nautico.entity.Miembro;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.repository.BarcoRepository;
import com.paulasanchez.club_nautico.repository.MiembroRepository;
import com.paulasanchez.club_nautico.repository.PersonaRepository;


@Service
public class BarcoServiceImpl implements BarcoService {

	@Autowired
    BarcoRepository barcoRepository;

    @Autowired
    PersonaRepository personaRepository;


    @Autowired
    MiembroRepository miembroRepository;
    
	@Override
	public List<Barco> findAllBarcos() {
        return barcoRepository.findAll();

	}

	@Override
	public Barco findBarcoById(String matricula) throws NotFoundException {
        return barcoRepository.findById(matricula).orElseThrow(()-> new NotFoundException("Barco con matricula " + matricula + " no encontrado"));

	}

	@Override
	public Barco saveBarco(Barco barco) throws DuplicateException, NotFoundException {
		int id = barco.getPropietario().getId();
		Optional <Miembro> miembro= miembroRepository.findById(id);
		
		 if(!miembro.isPresent()){
	            throw new NotFoundException("No existe persona con el dni: "+id);
	        }else if(barcoRepository.existsByMatricula(barco.getMatricula())){
	            throw new DuplicateException("El barco con matricula "+barco.getMatricula()+" ya esta registrado");
	        }else {
	            return barcoRepository.save(barco);
	        }
	}

	@Override
	public Barco updateBarco(String matricula, Barco barco) throws NotFoundException {
		 if(!barcoRepository.existsById(matricula)){
	            throw new NotFoundException("Barco con matricula " + matricula + " no encontrado");
	        }else {
	            Barco barco_db = barcoRepository.findById(matricula).get();
	            if (Objects.nonNull(barco.getNombre()) && !"".equalsIgnoreCase(barco.getNombre())) {
	                barco_db.setNombre(barco.getNombre());
	            }
	            if (Objects.nonNull(barco.getAmarre()) ) {
	                barco_db.setAmarre(barco.getAmarre());

	            }
	            if (barco.getTarifa() > 0) {
	                barco_db.setTarifa(barco.getTarifa());
	            }


	            return barcoRepository.save(barco_db);
	        }
	}

	@Override
	public Barco deleteBarco(String matricula) throws NotFoundException {
		Barco barco = barcoRepository.findById(matricula)
                .orElseThrow(() -> new NotFoundException("Barco no encontrado con matrícula: " + matricula));
        barcoRepository.deleteById(matricula);
        return barco;
	}


}
