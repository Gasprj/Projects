package com.example.demo.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
public class Usuario implements Serializable, Comparable<Usuario>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seqUser", initialValue = 06)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
	private int id;
	@NotBlank
	private String nome;
	@NotBlank
	private String telefone;
	@NotBlank
	private String email;
	@NotBlank
	private String senha;
	private int administrador;
	
	public Usuario() {
	}

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario(String nome, String telefone, String email, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
	}
	
	public int getAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(int administrador) {
		this.administrador = administrador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, senha, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public int compareTo(Usuario user) {
		return this.getNome().compareToIgnoreCase(user.getNome());	
	}

}
