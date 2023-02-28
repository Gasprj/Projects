package com.example.demo.security;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.Usuario;
import com.example.demo.model.repositories.UsuariosRepository;

@Service
public class LoginUsuario {
	
	@Autowired
	UsuariosRepository ur;
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void saveUser(Usuario user){
		try {
		if(ur.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("Email já cadastrado");
		
			user.setSenha(Cripto.crypt(user.getSenha()));
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Erro para criptografar a senha");
		}
		ur.save(user);
	}
	
	public Usuario loginUser(String email, String senha) {
		this.usuario = ur.checkLogin(email, senha);
		return this.usuario;
	}

}
