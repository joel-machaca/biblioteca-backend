package pe.edu.idat.biblioteca.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.biblioteca.dto.UsuarioAdminRequest;
import pe.edu.idat.biblioteca.dto.UsuarioAdminResponse;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioRequest;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioResponse;
import pe.edu.idat.biblioteca.entity.Rol;
import pe.edu.idat.biblioteca.entity.Usuario;
import pe.edu.idat.biblioteca.mapper.UsuarioAdminMapper;
import pe.edu.idat.biblioteca.mapper.UsuarioMapper;
import pe.edu.idat.biblioteca.repository.RolRepository;
import pe.edu.idat.biblioteca.repository.UsuarioRepository;
import pe.edu.idat.biblioteca.service.UsuarioService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioAdminMapper usuarioAdminMapper;

    @Transactional
    @Override
    public UsuarioResponse createAccount(UsuarioRequest usuarioRequest) {
        if(usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()){
            throw new RuntimeException("el email ingresado ya esta registrado,intente otra vez");
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);

        Rol rol=rolRepository.findByNombre("USER")
                .orElseThrow(()->new RuntimeException("rol USER no existe"));
        usuario.getRoles().add(rol);


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

    @Override
    public UsuarioResponse updateUsuario(Long id, UsuarioRequest usuarioRequest) {
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("usuario inexistente:" + id));

        usuarioMapper.updateEntityFromRequest(usuarioRequest,usuario);

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("usuario inexistente:" + id));

        usuarioRepository.delete(usuario);
    }












    //    @Transactional
//    public UsuarioAdminResponse createUserByAdmin(UsuarioAdminRequest usuarioAdminRequest) {
//        if (usuarioRepository.findByEmail(usuarioAdminRequest.email()).isPresent()) {
//            throw new RuntimeException("El email ya está registrado");
//        }
//
//        Usuario usuario = usuarioAdminMapper.toEntity(usuarioAdminRequest);
//
//
//        Set<Rol> roles = usuarioAdminRequest.rolesIds().stream()
//                .map(id -> rolRepository.findById(id)
//                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + id)))
//                .collect(Collectors.toSet());
//
//        usuario.setRoles(roles);
//
//        return usuarioAdminMapper.toResponse(usuario);
//    }
}
