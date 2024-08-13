package br.edu.ifpr.projeto03.repositorio;


import org.springframework.data.repository.CrudRepository;


import br.edu.ifpr.projeto03.model.Empresa;


public interface EmpresaRepositorio extends CrudRepository<Empresa, Long> {
    
    

}
