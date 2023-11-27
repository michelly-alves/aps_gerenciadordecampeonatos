package GerenciadorCampeonato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import GerenciadorCampeonato.Model.Time;

@Repository
public interface TimeRepository extends CrudRepository<Time, Integer> {

    @Query("SELECT t.id FROM Time t")
    List<Integer> getAllIds();
}