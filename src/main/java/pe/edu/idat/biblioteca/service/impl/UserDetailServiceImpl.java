package pe.edu.idat.biblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.idat.biblioteca.entity.Usuario;
import pe.edu.idat.biblioteca.repository.UsuarioRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario= usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("usuario no encontrado con el email:" + email));

        Set<GrantedAuthority> authorities=usuario.getRoles().stream()
                .map(rol-> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toSet());

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .disabled(!usuario.getEnabled())
                .authorities(authorities)
                .build();
    }



}
