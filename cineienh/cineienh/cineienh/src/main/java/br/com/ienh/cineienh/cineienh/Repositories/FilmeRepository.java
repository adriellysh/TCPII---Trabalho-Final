package br.com.ienh.cineienh.cineienh.Repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ienh.cineienh.cineienh.Entities.Filme;

public interface FilmeRepository extends CrudRepository<Filme, Integer> {
    
}