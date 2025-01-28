package com.paulasanchez.club_nautico.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paulasanchez.club_nautico.config.JwtService;
import com.paulasanchez.club_nautico.controllers.models.AuthResponse;
import com.paulasanchez.club_nautico.controllers.models.AuthenticationRequest;
import com.paulasanchez.club_nautico.controllers.models.RegisterRequest;
import com.paulasanchez.club_nautico.entity.Persona;
import com.paulasanchez.club_nautico.entity.Role;
import com.paulasanchez.club_nautico.repository.PersonaRepository;

@Service
public class AuthServiceImpl implements AuthService {

  private final PersonaRepository personaRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthServiceImpl(PersonaRepository personaRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.personaRepository = personaRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public AuthResponse register(RegisterRequest request) {
    var user = Persona.builder()
        .dni(request.getDni())
        .nombre(request.getNombre())
        .apellidos(request.getApellidos())
        .telefono(request.getTelefono())
        .direccion(request.getDireccion())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    personaRepository.save(user);

    var jwtToken = jwtService.generateToken(user);

    return AuthResponse.builder().token(jwtToken).build();

  }

  @Override
  public AuthResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()));
    var persona = personaRepository.findPersonaByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(persona);
    return AuthResponse.builder().token(jwtToken).build();
  }
}
