package GerenciadorCampeonato.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import GerenciadorCampeonato.Model.Jogador;

@Repository
public interface JogadorRepository extends CrudRepository<Jogador, Integer> {
    
}
