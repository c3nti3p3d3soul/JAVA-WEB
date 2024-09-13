package br.edu.ifpr.restapidelivery.repositorio;

import br.edu.ifpr.restapidelivery.model.PedidoProduto;
import br.edu.ifpr.restapidelivery.model.PedidoProdutoId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepositorio extends CrudRepository<PedidoProduto, PedidoProdutoId> {
}
