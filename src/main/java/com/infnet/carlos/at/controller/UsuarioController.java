package com.infnet.carlos.at.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.infnet.carlos.at.model.Usuario;
import com.infnet.carlos.at.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/public")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable String id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNome(usuarioDetails.getNome());
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioDetails.getSenha()));
            usuario.setPapel(usuarioDetails.getPapel());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @DeleteMapping("/usuarios/{id}")
    public String deleteUsuario(@PathVariable String id) {
        usuarioRepository.deleteById(id);
        return "Usuário removido com sucesso";
    }
}
