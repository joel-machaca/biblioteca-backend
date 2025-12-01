package pe.edu.idat.biblioteca.mapper;

import org.mapstruct.Mapper;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioRequest;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioResponse;
import pe.edu.idat.biblioteca.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse toResponse(Usuario usuario);

    Usuario toEntity(UsuarioRequest usuarioRequest);
}
