//package com.paulasanchez.club_nautico.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.paulasanchez.club_nautico.controllers.models.AuthResponse;
//import com.paulasanchez.club_nautico.controllers.models.AuthenticationRequest;
//import com.paulasanchez.club_nautico.controllers.models.RegisterRequest;
//import com.paulasanchez.club_nautico.service.AuthService;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//@CrossOrigin("http://localhost:4200")
//public class AuthController {
//
//  private final AuthService authService;
//
//  @PostMapping("/register")
//  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
//    return ResponseEntity.ok(authService.register(request));
//  }
//
//  @PostMapping("/authenticate")
//  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {
//    return ResponseEntity.ok(authService.authenticate(request));
//  }
//
//}
