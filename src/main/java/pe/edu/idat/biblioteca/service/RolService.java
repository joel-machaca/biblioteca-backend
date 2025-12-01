package pe.edu.idat.biblioteca.service;

import pe.edu.idat.biblioteca.dto.rol.RolRequest;
import pe.edu.idat.biblioteca.dto.rol.RolResponse;
import pe.edu.idat.biblioteca.entity.Rol;

import java.util.List;

public interface RolService {
    RolResponse createRol (RolRequest rolRequest);
    List<RolResponse> listarRoles();
    RolResponse findById(Long id);
}
