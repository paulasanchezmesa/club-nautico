package com.paulasanchez.club_nautico.service;

import java.util.List;

import com.paulasanchez.club_nautico.entity.Barco;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;

public interface BarcoService {
	List<Barco> findAllBarcos();
    Barco findBarcoById(String matricula) throws NotFoundException;
    Barco saveBarco(Barco barco) throws DuplicateException, NotFoundException;
    Barco updateBarco(String matricula, Barco barco)throws NotFoundException;
    Barco deleteBarco(String matricula) throws NotFoundException;
}
