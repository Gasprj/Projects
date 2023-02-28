package com.example.demo.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entities.Usuario;

public interface UsuariosRepository extends CrudRepository<Usuario, Integer>{

	Usuario findById(int id);
	
	Usuario findByNome(String nome);

	Usuario findByEmail(String email);

	@Query("SELECT u FROM Usuario u WHERE u.email=:email AND u.senha=:senha")
	Usuario checkLogin(String email, String senha);
}
