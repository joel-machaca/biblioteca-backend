package pe.edu.idat.biblioteca.dto.usuario;

public record UsuarioResponse (
        Long idUsuario,
        String nombre,
        String apellido,
        String telefono,
        String email,
        String password,
        String rol
){
}
