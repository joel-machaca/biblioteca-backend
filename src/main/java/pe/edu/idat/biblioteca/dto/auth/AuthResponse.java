package pe.edu.idat.biblioteca.dto.auth;

public record AuthResponse (
        String token,
        String tipo,
        String email,
        String roles
){
}
