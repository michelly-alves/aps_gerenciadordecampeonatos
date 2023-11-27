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

import GerenciadorCampeonato.Model.Jogador;
import GerenciadorCampeonato.repository.JogadorRepository;

@RestController
@RequestMapping("/api/jogador")
public class JogadorController {
    
    @Autowired
    JogadorRepository jogadorRepository;

    @GetMapping
    public List<Jogador> getAllJogadores() {
        return (List<Jogador>) jogadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Jogador getJogadorById(@PathVariable int id) {
        return jogadorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Jogador save(@RequestBody Jogador jogador) {
            return jogadorRepository.save(jogador);
    }

    @PutMapping("/{id}")
    public Jogador updateJogador(@PathVariable int id, @RequestBody Jogador novoJogador) {
        Jogador jogadorExistente = jogadorRepository.findById(id).orElse(null);
        if (jogadorExistente != null) {
            jogadorExistente.setNome(novoJogador.getNome());
            return jogadorRepository.save(jogadorExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteJogador(@PathVariable int id) {
        jogadorRepository.deleteById(id);
    }
}
