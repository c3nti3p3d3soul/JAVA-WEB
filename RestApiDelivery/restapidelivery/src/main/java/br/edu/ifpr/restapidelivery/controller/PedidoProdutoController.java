package br.edu.ifpr.restapidelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpr.restapidelivery.model.Pedido;
import br.edu.ifpr.restapidelivery.model.Produto;
import br.edu.ifpr.restapidelivery.model.PedidoProduto;
import br.edu.ifpr.restapidelivery.repositorio.PedidoRepositorio;
import br.edu.ifpr.restapidelivery.repositorio.ProdutoRepositorio;
import br.edu.ifpr.restapidelivery.repositorio.PedidoProdutoRepositorio;

@RestController
@RequestMapping("/pedido-produto")
public class PedidoProdutoController {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private PedidoProdutoRepositorio pedidoProdutoRepositorio;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarProdutoAoPedido(@RequestParam Long pedidoId, @RequestParam Long produtoId) {
        // Buscar o pedido e o produto pelos IDs fornecidos
        Pedido pedido = pedidoRepositorio.findById(pedidoId).orElse(null);
        Produto produto = produtoRepositorio.findById(produtoId).orElse(null);

        // Verificar se o pedido e o produto existem
        if (pedido == null) {
            return ResponseEntity.badRequest().body("Pedido não encontrado");
        }
        if (produto == null) {
            return ResponseEntity.badRequest().body("Produto não encontrado");
        }

        // Verificar se o pedido está no estado válido para adicionar produtos
        if (pedido.getEntrega() == null || pedido.getEntrega().isEmpty()) {
            return ResponseEntity.badRequest().body("O pedido deve ter um endereço de entrega antes de adicionar produtos");
        }

        // Verificar se o produto já está adicionado ao pedido
        boolean produtoExistente = pedido.getPedidoProdutos().stream()
                .anyMatch(pp -> pp.getProduto().equals(produto));
        if (produtoExistente) {
            return ResponseEntity.badRequest().body("O produto já está adicionado ao pedido");
        }

        // Criar uma nova instância de PedidoProduto e salvar no repositório
        PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto);
        pedidoProdutoRepositorio.save(pedidoProduto);

        return ResponseEntity.ok("Produto adicionado ao pedido com sucesso!");
    }
}
