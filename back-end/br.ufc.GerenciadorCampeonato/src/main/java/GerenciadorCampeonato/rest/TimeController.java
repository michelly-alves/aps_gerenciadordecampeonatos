package GerenciadorCampeonato.rest;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciadorCampeonato.Model.Time;
import GerenciadorCampeonato.repository.TimeRepository;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    
    @Autowired
    TimeRepository timeRepository;

    @GetMapping
    public List<Time> getAllCompeticoes() {
        return (List<Time>) timeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Time getTimeById(@PathVariable int id) {
        return timeRepository.findById(id).orElse(null);
    }
    
    //Sorteio de times
    
    @GetMapping("/sorteio")
    public Time getSorteio() {
    	
        List<Integer> allIds = timeRepository.getAllIds();

        if (allIds.isEmpty()) {
            return null; 
        }
        
        Random gerador = new Random();
        int randomIndex = gerador.nextInt(allIds.size());
        int randomId = allIds.get(randomIndex);

        Time timeSorteado = timeRepository.findById(randomId).orElse(null);

        return timeSorteado;
    	
    }

    @PostMapping
    public Time save(@RequestBody Time time) {
            return timeRepository.save(time);
    }

   /* @GetMapping("/usuario")
    public List<Time> getTimesDoUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            return timeRepository.findTimesByUsuario(usuario);
        }
        return Collections.emptyList();
    }*/

    @PutMapping("/{id}")
    public Time updateTime(@PathVariable int id, @RequestBody Time novoTime) {
        Time timeExistente = timeRepository.findById(id).orElse(null);
        if (timeExistente != null) {
            timeExistente.setNome(novoTime.getNome());
            timeExistente.setImagem(novoTime.getImagem());
            timeExistente.setAbreviacao(novoTime.getAbreviacao());
            return timeRepository.save(timeExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTime(@PathVariable int id) {
        timeRepository.deleteById(id);
    }
}
