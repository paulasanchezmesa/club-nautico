package com.paulasanchez.club_nautico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulasanchez.club_nautico.entity.Persona;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.service.PersonaService;

@RestController
@RequestMapping("/api")
public class PersonaController {

	@Autowired
    private PersonaService personaService;


    @GetMapping("/findAllPersonas")
    public ResponseEntity<List<Persona>> findAllPersonas(){
        return ResponseEntity.status(HttpStatus.OK).body(personaService.findAllPersonas());
    }

    @GetMapping("/findPersonaById/{dni}")
    public ResponseEntity<Persona> findPersonaById(@PathVariable String dni) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(personaService.findPersonaById(dni));
    }


    @PostMapping("/savePersonas")
    public ResponseEntity<Persona> savePersona(@RequestBody Persona Persona) throws DuplicateException {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePersona(Persona));
    }

    @PutMapping("/updatePersonas/{dni}")
    public ResponseEntity<Persona> updatePersona(@PathVariable String dni, @RequestBody Persona Persona) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(personaService.updatePersona(dni, Persona));
    }

    @DeleteMapping("/deletePersonas/{dni}")
    public ResponseEntity<Persona> deletePersona(@PathVariable String dni) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(personaService.deletePersona(dni));
    }

}
