package pe.edu.idat.biblioteca.mapper;

import org.mapstruct.Mapper;
import pe.edu.idat.biblioteca.dto.libro.LibroRequest;
import pe.edu.idat.biblioteca.dto.libro.LibroResponse;
import pe.edu.idat.biblioteca.entity.Libro;

@Mapper(componentModel = "spring")
public interface LibroMapper {

    LibroResponse toResponse (Libro libro);

    Libro toEntity(LibroRequest libroRequest);
}
