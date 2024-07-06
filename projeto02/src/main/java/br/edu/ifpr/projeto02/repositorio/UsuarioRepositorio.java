
package br.edu.ifpr.projeto02.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifpr.projeto02.model.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> { 

    Optional<Usuario> findByLogin (@Param("login") String login);
    
}