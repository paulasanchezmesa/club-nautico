package com.paulasanchez.club_nautico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulasanchez.club_nautico.entity.Viaje;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.service.ViajeService;

@RestController
@RequestMapping("/api")
public class ViajeController {

	 @Autowired
	    ViajeService viajeService;

	    @GetMapping("/findAllViajes")
	    public ResponseEntity<List<Viaje>> findAllViajes(){
	        return ResponseEntity.status(HttpStatus.OK).body(viajeService.findAllViajes());
	    }

	    @GetMapping("/findViajeById/{id}")
	    public ResponseEntity<Viaje> findViajeById(@PathVariable Integer id) throws NotFoundException {
	        return ResponseEntity.status(HttpStatus.OK).body(viajeService.findViajeById(id));}

	    @PostMapping("/saveViaje")
	    public ResponseEntity<Viaje> saveViaje(@RequestBody Viaje viaje) throws DuplicateException, NotFoundException {
	        return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.saveViaje(viaje));
	    }

	    @PutMapping("/updateViaje/{id}")
	    public ResponseEntity<Viaje> updateViaje(@PathVariable Integer id,@RequestBody Viaje viaje) throws NotFoundException {
	        return ResponseEntity.status(HttpStatus.OK).body(viajeService.updateViaje(id, viaje));
	    }

	    @DeleteMapping("/deleteViaje/{id}")
	    public ResponseEntity<Viaje> deleteViaje(@PathVariable Integer id) throws NotFoundException {
	        return ResponseEntity.status(HttpStatus.OK).body(viajeService.deleteViaje(id));
	    }
}
