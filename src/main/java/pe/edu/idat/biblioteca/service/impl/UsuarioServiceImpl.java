package pe.edu.idat.biblioteca.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioRequest;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioResponse;
import pe.edu.idat.biblioteca.entity.Usuario;
import pe.edu.idat.biblioteca.mapper.UsuarioMapper;
import pe.edu.idat.biblioteca.repository.RolRepository;
import pe.edu.idat.biblioteca.repository.UsuarioRepository;
import pe.edu.idat.biblioteca.service.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    @Override
    public UsuarioResponse createAccount(UsuarioRequest usuarioRequest) {
        if(usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()){
            throw new RuntimeException("el email ingresado ya esta registrado,intente otra vez");
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);



        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse findById(Long id) {
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("no es encontro el usuario"));
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }
}
