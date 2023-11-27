package GerenciadorCampeonato.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import GerenciadorCampeonato.Model.Usuario;
import GerenciadorCampeonato.repository.UsuarioRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @GetMapping("/me")
    Principal getMet(Principal me) {
        return me;
    }

    @GetMapping("/{email}")
    public Usuario getUsuarioByEmail(@PathVariable String email) {
        return usuarioRepository.findById(email).orElse(null);
    }

    @PostMapping("/register")
    public Usuario save(@RequestBody Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.saveAndFlush(usuario);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String email, @RequestBody Usuario novoUsuario) {
        Usuario usuarioExistente = usuarioRepository.findById(email).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNomeCompleto(novoUsuario.getNomeCompleto());
            usuarioExistente.setUsername(novoUsuario.getUsername());
           // usuarioExistente.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
            usuarioExistente.setAvatar(novoUsuario.getAvatar());
            Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
            return ResponseEntity.ok(usuarioAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{email}")
    public void deleteUsuario(@PathVariable String email) {
        usuarioRepository.deleteById(email);

    }

}
