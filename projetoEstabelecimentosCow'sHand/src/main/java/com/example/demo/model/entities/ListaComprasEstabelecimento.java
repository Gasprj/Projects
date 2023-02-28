package com.example.demo.model.entities;

import java.util.Objects;

public class ListaComprasEstabelecimento implements Comparable<ListaComprasEstabelecimento> {

	private int id;
	private String nome;
	private String preco;
	
	public ListaComprasEstabelecimento() {
	}
	
	public ListaComprasEstabelecimento(int id, String nome, String preco) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaComprasEstabelecimento other = (ListaComprasEstabelecimento) obj;
		return id == other.id && Objects.equals(nome, other.nome) && Objects.equals(preco, other.preco);
	}
	
	@Override
	public int compareTo(ListaComprasEstabelecimento listComp) {
		return this.getNome().compareToIgnoreCase(listComp.getNome());	
	}
	
}
