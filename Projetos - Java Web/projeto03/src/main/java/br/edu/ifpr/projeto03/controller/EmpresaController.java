package br.edu.ifpr.projeto03.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto03.model.Empresa;
import br.edu.ifpr.projeto03.repositorio.EmpresaRepositorio;
import jakarta.validation.Valid;
@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {
     
 
        @Autowired
        private EmpresaRepositorio _empresaRepositorio;
        //http://127.0.0.1:8081/empresa/
        @PostMapping(value = "/")

        public ResponseEntity<?> cadastrar(@RequestBody @Valid Empresa empresa, BindingResult validacao){
       Map<String, Object> map = new HashMap<>();

       if (validacao.hasErrors()) {
            map.put("erros", validacao.getAllErrors());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
       } else {
        try {
            Empresa novaEmpresa = _empresaRepositorio.save(empresa);
            map.put("dados", novaEmpresa);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception e) {
         map.put("erro", e.getMessage());
         return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        
       }
    
        
    }
}
