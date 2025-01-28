package com.paulasanchez.club_nautico.service;

import com.paulasanchez.club_nautico.controllers.models.AuthResponse;
import com.paulasanchez.club_nautico.controllers.models.AuthenticationRequest;
import com.paulasanchez.club_nautico.controllers.models.RegisterRequest;

public interface AuthService {

  AuthResponse register(RegisterRequest request);

  AuthResponse authenticate(AuthenticationRequest request);

}
