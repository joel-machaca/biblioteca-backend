package pe.edu.idat.biblioteca.dto.usuario;

import java.util.Set;

public record UsuarioRequest (
        String nombre,
        String apellido,
        String telefono,
        String email,
        String password
){}
