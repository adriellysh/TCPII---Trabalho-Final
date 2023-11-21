package br.com.ienh.cineienh.cineienh.Controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ienh.cineienh.cineienh.Entities.Filme;
import br.com.ienh.cineienh.cineienh.Repositories.FilmeRepository;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    FilmeRepository filmeRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        Iterable<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("filmes", filmes);
        return "filme/listar";
    }

    @GetMapping("/novo")
    public String novo(Filme filme) {
        return "filme/novo";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Filme filme, BindingResult result){
        if(result.hasErrors()){
            if(filme.getId() != 0){
                return "filme/alterar";
            }
            return "filme/novo";
        }
        filmeRepository.save(filme);
        return "redirect:/filme/listar";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") int id){
        Optional<Filme> filme = filmeRepository.findById(id);
        filmeRepository.delete(filme.orElse(null));
        return "redirect:/filme/listar";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") int id, Model model){
        Optional<Filme> filme = filmeRepository.findById(id);
        model.addAttribute("filme", filme.orElse(null));
        return "filme/alterar";
    }
}
