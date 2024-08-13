package br.edu.ifpr.projeto03.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto03.model.Setor;

public interface SetorRepositorio extends CrudRepository<Setor, Long> {
    
}
