package GerenciadorCampeonato.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import GerenciadorCampeonato.Model.Partida;

@Repository
public interface PartidaRepository extends CrudRepository <Partida, Integer> {
    
}