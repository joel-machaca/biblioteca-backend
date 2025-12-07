package pe.edu.idat.biblioteca.dto.usuario;

import pe.edu.idat.biblioteca.entity.Rol;

import java.util.Set;

public record UsuarioResponse (
        Long idUsuario,
        String nombre,
        String apellido,
        String telefono,
        String email,
        Set<Rol> roles
){
}
