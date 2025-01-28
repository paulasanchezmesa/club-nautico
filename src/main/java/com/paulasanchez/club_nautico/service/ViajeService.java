package com.paulasanchez.club_nautico.service;

import java.util.List;

import com.paulasanchez.club_nautico.entity.Viaje;
import com.paulasanchez.club_nautico.exception.DuplicateException;
import com.paulasanchez.club_nautico.exception.NotFoundException;

public interface ViajeService {
	List<Viaje> findAllViajes();
    Viaje findViajeById(int id_Viaje) throws NotFoundException;
    Viaje saveViaje(Viaje Viaje) throws DuplicateException, NotFoundException;
    Viaje updateViaje(Integer id,Viaje viaje)throws NotFoundException;
    Viaje deleteViaje(Integer id) throws NotFoundException;
}
