package com.example.demo.model.entities;

import java.util.List;
import java.util.Objects;

public class ExibirEstabelecimentoEvalorCompra implements Comparable<ExibirEstabelecimentoEvalorCompra> {

	private int id;
	private String nome;
	private String telefone;
	private String endereco;
	private String valorCompra;
	private int qtdItens;
	private int qtdItensTotal;
	
	List<ListaComprasEstabelecimento> listaComp;

	public ExibirEstabelecimentoEvalorCompra() {
	}

	public ExibirEstabelecimentoEvalorCompra(int id, String nome, List<ListaComprasEstabelecimento> listaComp) {
		this.id = id;
		this.nome = nome;
		this.listaComp = listaComp;
	}

	public ExibirEstabelecimentoEvalorCompra(int id, String nome, String endereco, String valorCompra, List<ListaComprasEstabelecimento> listaComp) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.valorCompra = valorCompra;
		this.listaComp = listaComp;
	}
	
	public ExibirEstabelecimentoEvalorCompra(int id, String nome, String endereco, String valorCompra,  int qtdItens, int qtdItensTotal, List<ListaComprasEstabelecimento> listaComp) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.valorCompra = valorCompra;
		this.listaComp = listaComp;
		this.qtdItens = qtdItens;
		this.qtdItensTotal = qtdItensTotal;
	}

	public ExibirEstabelecimentoEvalorCompra(int id, String nome, String telefone, String endereco, String valorCompra,
			int qtdItens, int qtdItensTotal, List<ListaComprasEstabelecimento> listaComp) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.valorCompra = valorCompra;
		this.qtdItens = qtdItens;
		this.qtdItensTotal = qtdItensTotal;
		this.listaComp = listaComp;
	}

	public List<ListaComprasEstabelecimento> getListaComp() {
		return listaComp;
	}
	
	public void setListaComp(List<ListaComprasEstabelecimento> listaComp) {
		this.listaComp = listaComp;
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

	public String getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(String valorCompra) {
		this.valorCompra = valorCompra;
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

	public int getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}
	
	public int getQtdItensTotal() {
		return qtdItensTotal;
	}

	public void setQtdItensTotal(int qtdItensTotal) {
		this.qtdItensTotal = qtdItensTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endereco, id, listaComp, nome, qtdItens, valorCompra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExibirEstabelecimentoEvalorCompra other = (ExibirEstabelecimentoEvalorCompra) obj;
		return Objects.equals(endereco, other.endereco) && id == other.id && Objects.equals(listaComp, other.listaComp)
				&& Objects.equals(nome, other.nome) && qtdItens == other.qtdItens
				&& Objects.equals(valorCompra, other.valorCompra);
	}
	
	@Override
	public int compareTo(ExibirEstabelecimentoEvalorCompra estab) {
		return this.getNome().compareToIgnoreCase(estab.getNome());	
	}
	
}
