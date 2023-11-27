package GerenciadorCampeonato.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciadorCampeonato.Model.Partida;
import GerenciadorCampeonato.repository.PartidaRepository;

@RestController
@RequestMapping("/api/partida")
public class PartidaController {
    
    @Autowired
    PartidaRepository partidaRepository;

    @GetMapping
    public List<Partida> getAllPartidaes() {
        return (List<Partida>) partidaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Partida getPartidaById(@PathVariable int id) {
        return partidaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Partida save(@RequestBody Partida partida) {
            return partidaRepository.save(partida);
    }

    @PutMapping("/{id}")
    public Partida updatePartida(@PathVariable int id, @RequestBody Partida novoPartida) {
        Partida partidaExistente = partidaRepository.findById(id).orElse(null);
        if (partidaExistente != null) {
            partidaExistente.setNome(novoPartida.getNome());
            return partidaRepository.save(partidaExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePartida(@PathVariable int id) {
        partidaRepository.deleteById(id);
    }
}
