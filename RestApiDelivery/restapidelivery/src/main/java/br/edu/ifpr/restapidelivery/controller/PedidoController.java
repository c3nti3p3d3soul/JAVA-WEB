package br.edu.ifpr.restapidelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import br.edu.ifpr.restapidelivery.model.Pedido;
import br.edu.ifpr.restapidelivery.model.Produto;
import br.edu.ifpr.restapidelivery.repositorio.PedidoRepositorio;
import br.edu.ifpr.restapidelivery.repositorio.ProdutoRepositorio;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @PostMapping
    public ResponseEntity<?> criarPedido(@Valid @RequestBody Pedido pedido, BindingResult result) {
        if (result.hasErrors()) {
            // Extrai as mensagens de erro de validação
            StringBuilder errorMessage = new StringBuilder("Erro de validação: ");
            result.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

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
        Optional<Pedido> pedidoOpt = pedidoRepositorio.findById(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoOpt.get());
    }

    // Endpoint para adicionar produtos a um pedido existente
    @PostMapping("/{pedidoId}/adicionar/{produtoId}")
    public ResponseEntity<String> adicionarProdutoAoPedido(@PathVariable Long pedidoId, @PathVariable Long produtoId) {
        Optional<Pedido> pedidoOpt = pedidoRepositorio.findById(pedidoId);
        Optional<Produto> produtoOpt = produtoRepositorio.findById(produtoId);

        if (pedidoOpt.isPresent() && produtoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            Produto produto = produtoOpt.get();


            if (pedido.getProdutos() == null) {
                pedido.setProdutos(new ArrayList<>());
            }

            // Adiciona o produto ao pedido
            pedido.getProdutos().add(produto);

            // Salva a atualização do pedido no banco de dados
            pedidoRepositorio.save(pedido);

            return ResponseEntity.ok("Produto adicionado ao pedido com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Pedido ou Produto não encontrado!");
        }
    }
}
