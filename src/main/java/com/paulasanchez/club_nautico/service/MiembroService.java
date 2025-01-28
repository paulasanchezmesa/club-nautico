package com.paulasanchez.club_nautico.service;

import java.util.List;

import com.paulasanchez.club_nautico.entity.Barco;
import com.paulasanchez.club_nautico.entity.Miembro;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;

public interface MiembroService {
	List<Miembro> findAllMiembros();
	List<Barco> findAllMiembroBarcos(Integer id) throws NotFoundException;
    Miembro findMiembroById(int id) throws NotFoundException;
    Miembro saveMiembro(Miembro miembro) throws DuplicateException, NotFoundException;
    Miembro updateMiembro(int id, Miembro miembro)throws NotFoundException;
    Miembro deleteMiembro(int id)throws NotFoundException;
}
