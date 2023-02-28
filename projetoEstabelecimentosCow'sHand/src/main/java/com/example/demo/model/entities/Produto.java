package com.example.demo.model.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
public class Produto implements Comparable<Produto>{
	
	@Id
	@SequenceGenerator(name = "seqProd", initialValue = 188)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProd")
	private int id;
	@NotBlank
	private String nome;
	private String preco;
	@ManyToOne
	Estabelecimento estabelecimento;
	
	public Produto() {
	}

	public Produto(String nome) {
		this.nome = nome;
	}
	
	public Produto(String nome, String preco) {
		this.nome = nome;
		this.preco = preco;
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

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estabelecimento, id, nome, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(estabelecimento, other.estabelecimento) && id == other.id
				&& Objects.equals(nome, other.nome) && Objects.equals(preco, other.preco);
	}

	@Override
	public int compareTo(Produto prod) {
		return this.getNome().compareToIgnoreCase(prod.getNome());	
	}

}
