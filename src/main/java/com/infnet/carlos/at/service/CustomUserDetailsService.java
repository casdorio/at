package com.infnet.carlos.at.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infnet.carlos.at.model.Usuario;
import com.infnet.carlos.at.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não cadastrado");
        }
        return org.springframework.security.core.userdetails.User.withUsername(usuario.getNome())
                .password(usuario.getSenha())
                .roles(usuario.getPapel())
                .build();
    }
}
