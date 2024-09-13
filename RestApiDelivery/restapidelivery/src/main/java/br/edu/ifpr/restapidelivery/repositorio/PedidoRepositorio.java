package br.edu.ifpr.restapidelivery.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import br.edu.ifpr.restapidelivery.model.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    
    // Método para encontrar pedidos pelo ID do usuário
    List<Pedido> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    // Método para encontrar um pedido pelo ID
    // O JpaRepository já fornece essa funcionalidade
    Optional<Pedido> findById(Long pedidoId);

    // Não é necessário um método específico para criar um pedido
    // Use o método save da interface JpaRepository
}
