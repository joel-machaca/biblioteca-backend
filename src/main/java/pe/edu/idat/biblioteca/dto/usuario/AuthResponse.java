package pe.edu.idat.biblioteca.dto.usuario;

public record AuthResponse (
        String token,
        String tipo,
        String email,
        String roles
){
}
