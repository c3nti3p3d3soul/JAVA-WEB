package br.edu.ifpr.projeto03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto03.model.Setor;
import br.edu.ifpr.projeto03.repositorio.SetorRepositorio;
import jakarta.validation.Valid;
    
@RestController
@RequestMapping(value = "/setor")
public class SetorController {
    

    @Autowired
    private SetorRepositorio _setorRepositorio;

    @PostMapping(value = "/")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Setor setor, BindingResult validacao) {

        Map<String, Object> map = new HashMap<String, Object>();

        if(validacao.hasErrors()) {

            map.put("erros", validacao.getAllErrors());

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);


        }   else{
                try{
                    Setor novoSetor = _setorRepositorio.save(setor);
                    map.put("dados", novoSetor);
                    return new ResponseEntity<>(map, HttpStatus.CREATED);

                }   catch (Exception e) {
                    map.put("erro", e.getMessage());

                    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

                }
            
        }
}
        @GetMapping(value = "/")
        public List<Setor> listarTodos(){
            return (List<Setor>) _setorRepositorio.findAll();

}

}
           
      

