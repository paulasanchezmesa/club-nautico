package com.paulasanchez.club_nautico.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulasanchez.club_nautico.controllers.models.AuthResponse;
import com.paulasanchez.club_nautico.controllers.models.AuthenticationRequest;
import com.paulasanchez.club_nautico.controllers.models.RegisterRequest;
import com.paulasanchez.club_nautico.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authService.authenticate(request));
  }

}
