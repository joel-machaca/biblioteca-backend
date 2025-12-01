package pe.edu.idat.biblioteca.dto.usuario;

public record AuthRequest (
        String email,
        String password
){
}
