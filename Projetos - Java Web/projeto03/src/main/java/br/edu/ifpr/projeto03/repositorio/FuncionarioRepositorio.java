package br.edu.ifpr.projeto03.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto03.model.Funcionario;

public interface FuncionarioRepositorio extends CrudRepository<Funcionario, Long> {
    

}
