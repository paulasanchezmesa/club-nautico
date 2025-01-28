package com.paulasanchez.club_nautico.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "barcos")
@Data               //Todos los getters y setters
@AllArgsConstructor //Todos los constructores de salida
@NoArgsConstructor  //El constructor sin parametros
public class Barco implements Serializable {

    @Id
    private String matricula;

    private String nombre;

    private int amarre;

    private double tarifa;

    @JoinColumn(name = "propietario",referencedColumnName = "id")
    @ManyToOne()
    private Miembro propietario;

    @JsonIgnore
    @OneToMany(mappedBy = "barco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Viaje> viajes;


    // Getters y setters

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getAmarre() { return amarre; }
    public void setAmarre(int amarre) { this.amarre = amarre; }

    public double getTarifa() { return tarifa; }
    public void setTarifa(double tarifa) { this.tarifa = tarifa; }

    public Miembro getPropietario() { return propietario; }
    public void setPropietario(Miembro propietario) { this.propietario = propietario; }

    public List<Viaje> getViajes() { return viajes; }
    public void setViajes(List<Viaje> viajes) { this.viajes = viajes; }
}
