package pe.edu.idat.biblioteca.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.idat.biblioteca.dto.prestamo.PrestamoRequest;
import pe.edu.idat.biblioteca.dto.prestamo.PrestamoResponse;
import pe.edu.idat.biblioteca.service.PrestamosService;

import java.util.List;

@RestController
@RequestMapping("v1/prestamo")
@RequiredArgsConstructor
public class PrestamoRestController {

    private final PrestamosService prestamosService;

    @PostMapping
    public ResponseEntity<PrestamoResponse> createPrestamo(@Valid @RequestBody PrestamoRequest prestamoRequest){
        return new ResponseEntity<>(prestamosService.createPrestamo(prestamoRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse>findById(@PathVariable Long id){
        return new ResponseEntity<>(prestamosService.findById(id),HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<PrestamoResponse>>listarPrestamo(){
        return new ResponseEntity<>(prestamosService.listarPrestamo(),HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PrestamoResponse>>listarPrestamoPorId(@PathVariable Long idUsuario){
        return new ResponseEntity<>(prestamosService.listarPrestamoPorId(idUsuario),HttpStatus.OK);
    }

    @GetMapping("/devolucion/{id}")
    public ResponseEntity<Void>devolverPrestamo(@PathVariable Long id){
        prestamosService.devolverPrestamo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
