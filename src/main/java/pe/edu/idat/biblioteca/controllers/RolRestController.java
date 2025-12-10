package pe.edu.idat.biblioteca.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.idat.biblioteca.dto.rol.RolRequest;
import pe.edu.idat.biblioteca.dto.rol.RolResponse;
import pe.edu.idat.biblioteca.service.RolService;

import java.util.List;

@RestController
@RequestMapping("/v1/rol")
@RequiredArgsConstructor
public class RolRestController {

    private final RolService rolService;

    @PostMapping
    public ResponseEntity<RolResponse> createRol(@Valid @RequestBody RolRequest rolRequest){
        return new ResponseEntity<>(rolService.createRol(rolRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RolResponse>> listarRoles(){
        return new ResponseEntity<>(rolService.listarRoles(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(rolService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponse>updateRol(@PathVariable Long id ,@Valid @RequestBody RolRequest rolRequest){
        return new ResponseEntity<>(rolService.updateRol(id,rolRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteRol(@PathVariable Long id){
        rolService.deleteRol(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
