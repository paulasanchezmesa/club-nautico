package com.paulasanchez.club_nautico.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "miembros")
@Data               //Todos los getters y setters
@AllArgsConstructor
@NoArgsConstructor  //El constructor sin parametros
public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "dni_persona",referencedColumnName = "dni")
    @OneToOne
    private Persona persona;  // Referencia a la persona

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonIgnoreProperties("propietario")
    private List<Barco> barcos= new ArrayList<>();  // Lista de barcos asociados al miembro

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Barco> getBarcos() {
		return barcos;
	}

	public void setBarcos(List<Barco> barcos) {
		this.barcos = barcos;
	}



}

