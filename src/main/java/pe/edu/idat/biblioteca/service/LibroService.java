package pe.edu.idat.biblioteca.service;

import pe.edu.idat.biblioteca.dto.libro.LibroRequest;
import pe.edu.idat.biblioteca.dto.libro.LibroResponse;

import java.util.List;

public interface LibroService {
    LibroResponse createLibro (LibroRequest libroRequest);
    LibroResponse findById(Long id);
    List<LibroResponse>listarLibros();

    void actualizarStock(Long id,Integer newStock); //segun esto va dentro de los metodos que implementaremos cada metodo
}
