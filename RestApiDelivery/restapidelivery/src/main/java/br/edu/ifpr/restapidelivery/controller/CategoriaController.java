package br.edu.ifpr.restapidelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.restapidelivery.model.Categoria;
import br.edu.ifpr.restapidelivery.repositorio.CategoriaRepositorio;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = (List<Categoria>) categoriaRepositorio.findAll();
        return ResponseEntity.ok(categorias);
    }
}
