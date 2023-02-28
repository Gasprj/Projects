package com.example.demo.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entities.Estabelecimento;
import com.example.demo.model.entities.Produto;

public interface ProdutosRepository extends CrudRepository<Produto, Integer>{

	Iterable <Produto> findByEstabelecimento(Estabelecimento estab);
	
	Produto findById(int id);
	
	Produto findByNome(String nome);
	
}
