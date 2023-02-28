package com.example.demo.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entities.Estabelecimento;

public interface EstabelecimentoRepository extends CrudRepository<Estabelecimento, Integer>{

	Estabelecimento findById(int id);
	
	Estabelecimento findByNomeIgnoreCase(String nome);
	
	public Iterable<Estabelecimento> findByNomeContainingIgnoreCase(String parteDoNome);
	
}
