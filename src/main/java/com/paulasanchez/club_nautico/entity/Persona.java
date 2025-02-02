package com.paulasanchez.club_nautico.entity;

import java.util.Collection;
import java.util.List;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persona// implements UserDetails
{

  @Id
  private String dni;
  private String nombre;
  private String apellidos;
  private String telefono;
  private String direccion;
  @Column(name="es_patron")
  private boolean esPatron;

  public boolean getEsPatron() {
    return esPatron;
  }

//  private String email;
//  private String password;

//  @Enumerated(EnumType.ORDINAL)
//  private Role role;
//
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return List.of(new SimpleGrantedAuthority(role.name()));
//  }
//
//  @Override
//  public String getPassword() {
//    return password;
//  }
//
//  @Override
//  public String getUsername() {
//    return email;
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    // Indica si la cuenta del usuario está bloqueada
//    return true; // Temporal, cámbialo según tu lógica
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return true;
//  }
}
