package com.paulasanchez.club_nautico.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "viajes")
@Data               //Todos los getters y setters
@AllArgsConstructor //Todos los constructores de salida
@NoArgsConstructor
public class Viaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "organizador_id",referencedColumnName = "dni")
    private Persona organizador;

    @ManyToOne
    @JoinColumn(name = "barco_id",referencedColumnName = "matricula")
    private Barco barco;

    // Getters y setters
    public int getId() { return id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Persona getOrganizador() { return organizador; }
    public void setOrganizador(Persona organizador) { this.organizador = organizador; }

    public Barco getBarco() { return barco; }
    public void setBarco(Barco barco) { this.barco = barco; }
}

