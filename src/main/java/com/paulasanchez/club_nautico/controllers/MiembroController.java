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

import com.paulasanchez.club_nautico.entity.Barco;
import com.paulasanchez.club_nautico.entity.Miembro;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.service.MiembroService;

@RestController
@RequestMapping("/api")
public class MiembroController {

    @Autowired
    MiembroService miembroService;

    @GetMapping("/findAllMiembros")
    public ResponseEntity<List<Miembro>> findAllMiembros() {
        return ResponseEntity.status(HttpStatus.OK).body(miembroService.findAllMiembros());
    }

    @GetMapping("/findMiembroById/{id}")
    public ResponseEntity<Miembro> findMiembroById(@PathVariable Integer id ) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(miembroService.findMiembroById(id));
    }

    @GetMapping("/findAllMiembroBarcos/{id}")
    public ResponseEntity<List<Barco>> findAllPersonaBarcos(@PathVariable Integer id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(miembroService.findAllMiembroBarcos(id));
    }

    @PostMapping("/saveMiembro")
    public ResponseEntity<Miembro> saveMiembro(@RequestBody Miembro miembro) throws DuplicateException, NotFoundException{
    	System.out.println(miembro);
        return ResponseEntity.status(HttpStatus.CREATED).body(miembroService.saveMiembro(miembro));
    }

    @PutMapping("/updateMiembro/{id}")
    public ResponseEntity<Miembro> updateMiembro( @PathVariable Integer id, @RequestBody Miembro miembro) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(miembroService.updateMiembro(id,miembro));
    }

    @DeleteMapping("/deleteMiembro/{id}")
    public ResponseEntity<Miembro> deleteMiembro(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(miembroService.deleteMiembro(id));
    }
}
