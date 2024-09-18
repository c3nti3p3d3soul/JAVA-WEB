package br.edu.ifpr.restapidelivery.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import br.edu.ifpr.restapidelivery.model.Produto;

public interface ProdutoRepositorio extends CrudRepository<Produto, Long> {

    // Consulta para buscar produtos pelo nome
    @Query("SELECT p FROM Produto p WHERE p.nome = :nome")
    List<Produto> findByNome(@Param("nome") String nome);

    // Consulta para buscar produtos pela categoria
    @Query("SELECT p FROM Produto p WHERE p.categoria.id = :categoriaId")
    List<Produto> findByCategoriaId(@Param("categoriaId") Long categoriaId);
}
