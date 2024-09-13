package br.edu.ifpr.restapidelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import br.edu.ifpr.restapidelivery.model.Produto;

import br.edu.ifpr.restapidelivery.repositorio.ProdutoRepositorio;

import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;



    @GetMapping("/listar/{categoriaId}")
    public ResponseEntity<List<Produto>> listarProdutosPorCategoria(@PathVariable Long categoriaId) {
        List<Produto> produtos = produtoRepositorio.findByCategoriaId(categoriaId);
        return ResponseEntity.ok(produtos);
    }

 

}
