package pe.edu.idat.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.biblioteca.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
}
