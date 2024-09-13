package br.edu.ifpr.restapidelivery.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifpr.restapidelivery.model.Categoria;

public interface CategoriaRepositorio extends CrudRepository<Categoria, Long> {
    
     @Query(value = "SELECT * FROM categoria WHERE descricao = :descricao", nativeQuery = true)
    Optional<Categoria> findByDescricao(@Param("descricao") String descricao);
}
