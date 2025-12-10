package pe.edu.idat.biblioteca.service;

import pe.edu.idat.biblioteca.dto.auth.AuthResponse;
import pe.edu.idat.biblioteca.dto.auth.LoginRequest;
import pe.edu.idat.biblioteca.dto.jwt.JwtResponse;
import pe.edu.idat.biblioteca.dto.jwt.RefreshTokenRequest;

public interface AuthService {
//    AuthResponse login(LoginRequest loginRequest);

    JwtResponse login(LoginRequest loginRequest);

    JwtResponse refreshToken(RefreshTokenRequest request);
}
