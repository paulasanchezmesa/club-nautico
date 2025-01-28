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
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;
import com.paulasanchez.club_nautico.service.BarcoService;

@RestController
@RequestMapping("/api")
public class BarcoController {

	@Autowired
    BarcoService barcoService;

    @GetMapping("/findAllBarcos")
    public ResponseEntity<List<Barco>> findAllBarcos(){
        return ResponseEntity.status(HttpStatus.OK).body(barcoService.findAllBarcos());
    }

    @GetMapping("/findBarcoById/{matricula}")
    public ResponseEntity<Barco> findBarcoById(@PathVariable String matricula) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(barcoService.findBarcoById(matricula));}

    @PostMapping("/saveBarco")
    public ResponseEntity<Barco> saveBarco(@RequestBody Barco barco) throws DuplicateException, NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(barcoService.saveBarco(barco));
    }
    @PutMapping("/updateBarco/{matricula}")
    public ResponseEntity<Barco> updateBarco(@PathVariable("matricula") String matricula, @RequestBody Barco barco) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(barcoService.updateBarco(matricula,barco));
    }

    @DeleteMapping("/deleteBarco/{matricula}")
    public ResponseEntity<Barco> deleteBarco(@PathVariable("matricula")String matricula ) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(barcoService.deleteBarco(matricula));
    }
}
