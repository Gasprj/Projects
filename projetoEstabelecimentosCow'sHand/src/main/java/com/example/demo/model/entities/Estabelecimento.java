package com.example.demo.model.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class Estabelecimento implements Serializable, Comparable<Estabelecimento>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seqEstab", initialValue = 07)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEstab")
	private int id;
	@NotBlank
	private String nome;
	private String telefone;
	@NotBlank
	private String endereco;
	@OneToMany
	private List<Produto> produto;
	@Transient
	private int qtdItens;
	@Transient
	private ExibirEstabelecimentoEvalorCompra exibirProd;
	
	
	public Estabelecimento() {
	}

	public Estabelecimento(@NotBlank String nome, @NotBlank String endereco, List<Produto> produto) {
		this.nome = nome;
		this.endereco = endereco;
		this.produto = produto;
	}
	
	public Estabelecimento(@NotBlank String nome, @NotBlank String endereco, int qtdItens, List<Produto> produto) {
		this.nome = nome;
		this.endereco = endereco;
		this.produto = produto;
		this.qtdItens = qtdItens;
	}
	
	public Estabelecimento(@NotBlank String nome, String telefone, @NotBlank String endereco, int qtdItens, List<Produto> produto) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.produto = produto;
		this.qtdItens = qtdItens;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProdutos(List<Produto> produto) {
		this.produto = produto;
	}

	public int getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endereco, exibirProd, id, nome, produto, qtdItens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estabelecimento other = (Estabelecimento) obj;
		return Objects.equals(endereco, other.endereco) && Objects.equals(exibirProd, other.exibirProd)
				&& id == other.id && Objects.equals(nome, other.nome) && Objects.equals(produto, other.produto)
				&& qtdItens == other.qtdItens;
	}
	
	@Override
	public int compareTo(Estabelecimento estab) {
		return this.getNome().compareToIgnoreCase(estab.getNome());	
	}
	
}
