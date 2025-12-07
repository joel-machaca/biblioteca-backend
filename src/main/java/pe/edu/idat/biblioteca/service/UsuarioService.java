package pe.edu.idat.biblioteca.service;

import pe.edu.idat.biblioteca.dto.usuario.UsuarioRequest;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioResponse;


import java.util.List;

public interface UsuarioService {
    UsuarioResponse createAccount(UsuarioRequest usuarioRequest);
    UsuarioResponse findById(Long id);
    List<UsuarioResponse>listarUsuarios();


    UsuarioResponse updateUsuario(Long id ,UsuarioRequest usuarioRequest);
    void deleteUsuario(Long id);




//    UsuarioAdminResponse createUserByAdmin(UsuarioAdminRequest usuarioAdminRequest);
}
