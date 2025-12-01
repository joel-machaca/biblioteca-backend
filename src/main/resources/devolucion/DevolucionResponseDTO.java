package devolucion;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DevolucionResponseDTO (
        @NotNull
        Long idDevolucion,
        LocalDate fechaDevolucion,
        Estado estado,
        Long idPrestamo
){


}
