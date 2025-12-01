package pe.edu.idat.biblioteca.dto.prestamo;

import java.time.LocalDate;

public record PrestamoRequest (
        LocalDate fechaPrestamo,
        LocalDate fechaEntrega,
        Long idLibro,
        Long idUsuario,
        String estado
){}
