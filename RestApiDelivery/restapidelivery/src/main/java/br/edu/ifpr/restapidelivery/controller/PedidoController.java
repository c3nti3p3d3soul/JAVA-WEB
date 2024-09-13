package br.edu.ifpr.restapidelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ifpr.restapidelivery.model.Pedido;
import br.edu.ifpr.restapidelivery.repositorio.PedidoRepositorio;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        // Verificação básica para garantir que o pedido tenha um endereço de entrega
        if (pedido.getEntrega() == null || pedido.getEntrega().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Salvar o novo pedido
        Pedido novoPedido = pedidoRepositorio.save(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoRepositorio.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPedido(@PathVariable Long id) {
        Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }
}
