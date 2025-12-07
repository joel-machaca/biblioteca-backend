package pe.edu.idat.biblioteca.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.biblioteca.dto.libro.LibroRequest;
import pe.edu.idat.biblioteca.dto.libro.LibroResponse;
import pe.edu.idat.biblioteca.entity.Libro;
import pe.edu.idat.biblioteca.mapper.LibroMapper;
import pe.edu.idat.biblioteca.repository.LibroRepository;
import pe.edu.idat.biblioteca.service.LibroService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;

    @Transactional
    @Override
    public LibroResponse createLibro(LibroRequest libroRequest) {
        if(libroRepository.findByTitulo(libroRequest.titulo()).isPresent()){
            throw new RuntimeException("este libro ya existe ");
        }
        Libro libro=libroMapper.toEntity(libroRequest);
        return libroMapper.toResponse(libroRepository.save(libro));
    }


    @Override
    public LibroResponse findById(Long id) {
        Libro libro=libroRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no se encontro el libro con id: "+id));

        return libroMapper.toResponse(libro);
    }

    @Override
    public List<LibroResponse> listarLibros() {
        return libroRepository
                .findAll()
                .stream()
                .map(libroMapper::toResponse)
                .toList();
    }

    @Override
    public LibroResponse updateLibro(Long id, LibroRequest libroRequest) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("libro no encontrado: "+ id));

        libroMapper.updateEntityFromRequest(libroRequest, libro);

        return libroMapper.toResponse(libroRepository.save(libro));
    }

    @Override
    public void deleteLibro(Long id) {
        Libro libro=libroRepository.findById(id)
                .orElseThrow(()->new RuntimeException("libro inexistente: "+ id));

        libroRepository.delete(libro);
    }





    @Override
    public void reducirStock(Libro libro) {

        if (libro.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible para este libro");
        }

        libro.setStock(libro.getStock() - 1);
        libroRepository.save(libro);
    }

    public void aumentarStock(Libro libro) {
        libro.setStock(libro.getStock() + 1);
        libroRepository.save(libro);
    }
}
