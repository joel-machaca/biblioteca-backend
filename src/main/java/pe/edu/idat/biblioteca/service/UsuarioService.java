package pe.edu.idat.biblioteca.service;

import pe.edu.idat.biblioteca.dto.usuario.UsuarioRequest;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioResponse;
import pe.edu.idat.biblioteca.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse createAccount(UsuarioRequest usuarioRequest);
    UsuarioResponse findById(Long id);
    List<UsuarioResponse>listarUsuarios();
//    UsuarioResponse findByEmail(String email);
}
