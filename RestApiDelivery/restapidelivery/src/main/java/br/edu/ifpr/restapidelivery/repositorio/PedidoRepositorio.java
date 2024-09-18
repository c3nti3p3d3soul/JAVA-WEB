package br.edu.ifpr.restapidelivery.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import br.edu.ifpr.restapidelivery.model.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    
    List<Pedido> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    Optional<Pedido> findById(Long pedidoId);


}
