package pe.edu.idat.biblioteca.mapper;

import org.mapstruct.Mapper;
import pe.edu.idat.biblioteca.dto.rol.RolRequest;
import pe.edu.idat.biblioteca.dto.rol.RolResponse;
import pe.edu.idat.biblioteca.entity.Rol;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol toEntity(RolRequest rolRequest);

    RolResponse toResponse(Rol rol);
}
