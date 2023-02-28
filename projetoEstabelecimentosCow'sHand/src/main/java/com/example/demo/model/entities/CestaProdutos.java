package com.example.demo.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CestaProdutos implements Comparable<CestaProdutos>{
	
	private String nome;
	
	private Integer quantidade;
	
	private List<String> nomes = new ArrayList<String>();
	
	private List<Integer> quantidades = new ArrayList<Integer>();

	public CestaProdutos() {
	}

	public CestaProdutos(String nome, Integer quantidade) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public void setNomesUnitario(String nomes) {
		this.nomes.add(nomes);
	}
	
	public List<Integer> getQuantidades() {
		return quantidades;
	}

	public void setQuantidades(List<Integer> quantidades) {
		this.quantidades = quantidades;
	}
	
	public void setQuantidadesUnitario(Integer quantidades) {
		this.quantidades.add(quantidades);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome, nomes, quantidade, quantidades);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CestaProdutos other = (CestaProdutos) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(nomes, other.nomes)
				&& Objects.equals(quantidade, other.quantidade) && Objects.equals(quantidades, other.quantidades);
	}

	@Override
	public int compareTo(CestaProdutos prod) {
		return this.getNome().compareToIgnoreCase(prod.getNome());	
	}
	
}
