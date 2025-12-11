package pe.edu.idat.biblioteca.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.idat.biblioteca.dto.auth.AuthResponse;
import pe.edu.idat.biblioteca.dto.jwt.JwtResponse;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioRequest;
import pe.edu.idat.biblioteca.dto.usuario.UsuarioResponse;
import pe.edu.idat.biblioteca.entity.Rol;
import pe.edu.idat.biblioteca.entity.Usuario;

import pe.edu.idat.biblioteca.exception.RolNotFoundException;
import pe.edu.idat.biblioteca.exception.UsuarioNotFoundException;
import pe.edu.idat.biblioteca.mapper.UsuarioMapper;
import pe.edu.idat.biblioteca.repository.RolRepository;
import pe.edu.idat.biblioteca.repository.UsuarioRepository;
import pe.edu.idat.biblioteca.security.JwtUtil;
import pe.edu.idat.biblioteca.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public JwtResponse createAccount(UsuarioRequest usuarioRequest) {
        if(usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()){
            throw new UsuarioNotFoundException("el email ingresado ya esta registrado,intente otra vez");
        }

        Rol rol=rolRepository.findByNombre("USER")
                .orElseThrow(()->new RolNotFoundException("rol USER no existe"));

        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);

        usuario.setPassword(passwordEncoder.encode(usuarioRequest.password()));
        usuario.setFechaCreacion(LocalDate.now());
        usuario.getRoles().add(rol);

        usuarioRepository.save(usuario);

        String accessToken=jwtUtil.generateToken(usuario.getEmail());
        String refreshToken= jwtUtil.generateRefreshToken(usuario.getEmail());

        return new JwtResponse(accessToken,refreshToken,usuario.getEmail(),rol.getNombre());
    }



    @Override
    public UsuarioResponse findById(Long id) {
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException("no es encontro el usuario"));
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
                .orElseThrow(()-> new UsuarioNotFoundException("usuario inexistente:" + id));

        usuarioMapper.updateEntityFromRequest(usuarioRequest,usuario);

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException("usuario inexistente:" + id));

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
