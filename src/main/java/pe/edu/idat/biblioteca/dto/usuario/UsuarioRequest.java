package pe.edu.idat.biblioteca.dto.usuario;

public record UsuarioRequest (
        String nombre,
        String apellido,
        String telefono,
        String email,
        String password,
        Long idRol
){}
