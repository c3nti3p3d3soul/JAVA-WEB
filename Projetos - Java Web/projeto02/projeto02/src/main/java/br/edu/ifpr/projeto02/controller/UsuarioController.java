package br.edu.ifpr.projeto02.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto02.model.Usuario;
import br.edu.ifpr.projeto02.repositorio.UsuarioRepositorio;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio _usuarioRepositorio;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    // http://127.0.0.1:8081/usuario
    @GetMapping(value = "/")
    public List<Usuario> getAll() {
        return (List<Usuario>) _usuarioRepositorio.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> get(@PathVariable("id") long id) {
        Optional<Usuario> usuario = _usuarioRepositorio.findById(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid Usuario usuario, BindingResult validacao) {
        Map<String, Object> map = new HashMap<>();
        if (validacao.hasErrors()) {
            map.put("status", HttpStatus.BAD_REQUEST.value());
            map.put("erros", validacao.getAllErrors());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } else {
            try {
                usuario.setSenha(encoder.encode(usuario.getSenha()));
                Usuario user = _usuarioRepositorio.save(usuario);
                map.put("status", HttpStatus.CREATED.value());
                map.put("dados", user);
                return new ResponseEntity<>(map, HttpStatus.CREATED);
            } catch (Exception e) {
                map.put("status", HttpStatus.BAD_REQUEST.value());
                map.put("mensagem", e.getMessage());
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") long id) {
        Optional<Usuario> usuario = _usuarioRepositorio.findById(id);
        if (usuario.isPresent()) {
            _usuarioRepositorio.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable("id") long id, @RequestBody Usuario usuarioNovo) {
        Optional<Usuario> usuarioAntigo = _usuarioRepositorio.findById(id);
        if (usuarioAntigo.isPresent()) {
            Usuario usuario = usuarioAntigo.get();
            usuario.setNome(usuarioNovo.getNome());
            _usuarioRepositorio.save(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> result = _usuarioRepositorio.findByLogin(usuario.getLogin());
        if (result.isPresent()) {
            Usuario user = result.get();
            if (encoder.matches(usuario.getSenha(), user.getSenha())) {
                String chave = KeyGenerators.string().generateKey();
                _usuarioRepositorio.updateChave(chave, user.getId());
                Map<String, Object> map = new HashMap<>();
                map.put("chave", chave);
                return new ResponseEntity<>(map, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
