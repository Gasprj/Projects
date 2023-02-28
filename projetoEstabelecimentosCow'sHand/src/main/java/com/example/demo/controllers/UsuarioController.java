package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entities.CestaProdutos;
import com.example.demo.model.entities.Estabelecimento;
import com.example.demo.model.entities.ExibirEstabelecimentoEvalorCompra;
import com.example.demo.model.entities.ListaComprasEstabelecimento;
import com.example.demo.model.entities.Produto;
import com.example.demo.model.entities.Usuario;
import com.example.demo.model.repositories.EstabelecimentoRepository;
import com.example.demo.model.repositories.ProdutosRepository;
import com.example.demo.model.repositories.UsuariosRepository;
import com.example.demo.security.LoginUsuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	List <ExibirEstabelecimentoEvalorCompra> lev = new ArrayList<ExibirEstabelecimentoEvalorCompra>();
	List <ListaComprasEstabelecimento> lce = new ArrayList<ListaComprasEstabelecimento>();
	List<CestaProdutos> cestaProds= new ArrayList<CestaProdutos>();
	List<String> lis = new ArrayList<String>();
	List<Integer> quant = new ArrayList<Integer>();
	
	@Autowired
	LoginUsuario lu;
	
	@Autowired
	UsuariosRepository ur;
	
	@Autowired
	EstabelecimentoRepository er;
	
	@Autowired
	ProdutosRepository pr;
		
	
	@PostMapping("/novo")
	public String formSave(Usuario user,RedirectAttributes ra) {
		
		if(ur.findByEmail(user.getEmail()) != null) {
			ra.addFlashAttribute("mensagem", "Email já cadastrado!");
			return "redirect:/login";
		}else {
			user.setAdministrador(0);
			lu.saveUser(user);
			ra.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso,faça o login!");
		}	
		return "redirect:/login";
	}
	
	@GetMapping("/novoAdmin")
	public String formAdmin() {
		if(lu.getUsuario().getAdministrador() == -1)
			return "paginas/usuario/formAdmin";
		else
			return "redirect:/estabelecimento/lista";
	}
	
	@PostMapping("/novoAdmin")
	public String formSaveAdmin(Usuario user) {
		ModelAndView mv = new ModelAndView();
		
		if(ur.findByEmail(user.getEmail()) != null) {
			mv.addObject("msg", "Email já cadastrado");
			return formAdmin();
		}else {
			lu.saveUser(user);
		}	
		return "redirect:/estabelecimento/lista";
	}

	/*
	 * Formas diferentes de executar as páginas
	 */
	@GetMapping("/telas")
	public ModelAndView telasEstabs() {
		Estabelecimento est01 = er.findById(01);
		Iterable <Produto> prods01 = pr.findByEstabelecimento(est01);
		Estabelecimento est02 = er.findById(02);
		Iterable <Produto> prods02 = pr.findByEstabelecimento(est02);
		Estabelecimento est03 = er.findById(03);
		Iterable <Produto> prods03 = pr.findByEstabelecimento(est03);
		Estabelecimento est04 = er.findById(04);
		Iterable <Produto> prods04 = pr.findByEstabelecimento(est04);
		Estabelecimento est05 = er.findById(05);
		Iterable <Produto> prods05 = pr.findByEstabelecimento(est05);
		Estabelecimento est06 = er.findById(06);
		Iterable <Produto> prods06 = pr.findByEstabelecimento(est06);
		
		ModelAndView mv = new ModelAndView("paginas/usuario/telaInicial");
		mv.setViewName("paginas/usuario/telaInicial");
		mv.addObject("estab01", est01);
		mv.addObject("produtos01", prods01);
		mv.addObject("estab02", est02);
		mv.addObject("produtos02", prods02);
		mv.addObject("estab03", est03);
		mv.addObject("produtos03", prods03);
		mv.addObject("estab04", est04);
		mv.addObject("produtos04", prods04);
		mv.addObject("estab05", est05);
		mv.addObject("produtos05", prods05);
		mv.addObject("estab06", est06);
		mv.addObject("produtos06", prods06);

		return mv;
	}
	
	@GetMapping("/produtos")
	public ModelAndView criarCesta() {
		Iterable <Produto> listaProds = pr.findAll();
		List<Produto> listaComp = new ArrayList<Produto>();
		ModelAndView mv = new ModelAndView("paginas/usuario/produtosUser");
		
		if(this.quant.size() != this.lis.size() && (this.quant.size() > 0 || this.lis.size() > 0))
			mv.addObject("msg", "Verifique os campos selecionados");
		
		for (Produto pro : listaProds) {
			int cont = 0;
			
			for (Produto p : listaComp) {
				if(pro.getNome().equalsIgnoreCase(p.getNome()))
					cont++;
			}
			if(cont == 0)
				listaComp.add(pro);
		}
		Collections.sort(listaComp);
		
		mv.addObject("prods", listaComp);
		return mv;
	}
	
	/*
	 *  ################# Método Usando "String..." e "array" ######################
	 */
//	@RequestMapping("/exibirResultados")
//	public ModelAndView exibirResultados(@RequestParam String... produtos) {
////	public ModelAndView exibirResultados(@RequestParam String[] produtos) {
//		List <ExibirEstabelecimentoEvalorCompra> lev = new ArrayList<ExibirEstabelecimentoEvalorCompra>();
//		List <ListaComprasEstabelecimento> lce = new ArrayList<ListaComprasEstabelecimento>();
//		String[] cesta = produtos;
//		
//		for (Estabelecimento estab : er.findAll()) {
//			double soma = 0;
//			int qtdItens = 0;
//			
//			for (Produto prod : pr.findByEstabelecimento(estab)) {
//				for (int i = 0; i < cesta.length; i++) {
//					if(cesta[i].equalsIgnoreCase(prod.getNome())) {
//						if(!prod.getPreco().equals(null)) {
//							lce.add(new ListaComprasEstabelecimento(prod.getId(), prod.getNome(), prod.getPreco()));
//							soma += Double.parseDouble(prod.getPreco().replace(",", "."));
//							qtdItens++;
//						}
//					}
//				}
//			}
//			String somaTot = String.format("%.2f", soma);
//			lev.add(new ExibirEstabelecimentoEvalorCompra(estab.getId(), estab.getNome(), estab.getEndereco(), somaTot, qtdItens,  lce));
//		}
//		ModelAndView mv = new ModelAndView("paginas/usuario/exibirResultados");
//		mv.addObject("result", lev);
//		return mv;
//	}
	
	@RequestMapping("/exibirResultados")
	public ModelAndView exibirResultados(@RequestParam String[] produtos, @RequestParam Integer[] quantidade) {
		ModelAndView mv = new ModelAndView();
		this.cestaProds.clear();
		this.quant.clear();
		this.lis.clear();
		this.lce.clear();
		this.lev.clear();
		Stream.of(produtos).forEach(p -> lis.add(p));
		Integer[] qtd = this.validacaoQuatidade(quantidade);
		Stream.of(qtd).forEach(q -> quant.add(q));
		
		if(this.quant.size() != this.lis.size()) {
			return this.criarCesta();
		}
		
		this.preencherCesta(produtos, qtd);
		
		for (Estabelecimento estab : er.findAll()) {
			double soma = 0;
			int qtdItens = 0;
			
			for (Produto prod : pr.findByEstabelecimento(estab)) {
				for (int i = 0; i < produtos.length; i++) {
					if(produtos[i].equalsIgnoreCase(prod.getNome())) {
						if(!prod.getPreco().equals(null) && qtd[i] > 0) {
							this.lce.add(new ListaComprasEstabelecimento(prod.getId(), prod.getNome(), prod.getPreco()));
							double tot = Double.parseDouble(prod.getPreco().replace(",", ".")) * qtd[i];
							soma += tot;
							qtdItens++;
						}
					}
				}
			}
			String somaTot = String.format("%.2f", soma);
			this.lev.add(new ExibirEstabelecimentoEvalorCompra(estab.getId(), estab.getNome(), estab.getTelefone(), estab.getEndereco(), somaTot, qtdItens, produtos.length, lce));
		}
		mv.setViewName("paginas/usuario/exibirResultados");
		mv.addObject("result", this.lev);
		mv.addObject("list", this.lis);
		mv.addObject("qtd", this.quant);
		return mv;
	}
	
	private List<CestaProdutos> preencherCesta(String[] produtos, Integer[] quantidade) {
		for (int i = 0; i < produtos.length; i++) {
			this.cestaProds.add(new CestaProdutos(produtos[i], quantidade[i]));
		}
		return this.cestaProds;
	}
	
	private Integer[] validacaoQuatidade(Integer[] qtd) {
		List<Integer> qtdCerta = new ArrayList<Integer>();
		int cont = 0;
		for (int i = 0; i < qtd.length; i++) {
			if(qtd[i] != null && qtd[i] > 0) 
				qtdCerta.add(qtd[i]);
		}
		qtd = new Integer[qtdCerta.size()];
		for (Integer q : qtdCerta) {
			qtd[cont] = q;
			cont++;
		}
		return qtd;
	}
	
	@GetMapping("/exibirResultadosLista/{id}")
	public ModelAndView exibirResultadoDetalhado(@PathVariable("id") int id) {
		lce.clear();
		Estabelecimento est = er.findById(id);
		Iterable <Produto> listaComp = pr.findByEstabelecimento(est);
		
		for (Produto p : listaComp) {
			for (String s : this.lis) {
				if(s.equalsIgnoreCase(p.getNome()))
					lce.add(new ListaComprasEstabelecimento(p.getId(), p.getNome(), p.getPreco()));
			}
		}
		
		ModelAndView mv = new ModelAndView("paginas/usuario/exibirResultadoDetalhado");
		mv.addObject("estab", est);
		mv.addObject("result", this.lce);
		mv.addObject("cesta", this.cestaProds);
		return mv;
	}
	
	/*
	 *  ################# Método Obsoleto ######################
	 */
//	@GetMapping("/exibirResultados/{estab}")
//	public ModelAndView exibirResultadoEspecifico(@PathVariable String estab) {
//		ExibirEstabelecimentoEvalorCompra est = new ExibirEstabelecimentoEvalorCompra();
//		List <ListaComprasEstabelecimento> listaComp = new ArrayList<ListaComprasEstabelecimento>();
//		
//		for (Estabelecimento estabs : er.findAll()) {
//			if(estab.equalsIgnoreCase(estabs.getNome())) {
//				for (Produto prod : pr.findByEstabelecimento(estabs)) {
//					listaComp.add(new ListaComprasEstabelecimento(prod.getId(), prod.getNome(), prod.getPreco()));
//				}
//				est = new ExibirEstabelecimentoEvalorCompra(estabs.getId(), estabs.getNome(), listaComp);
//			}
//		}
//		
//		ModelAndView mv = new ModelAndView("paginas/usuario/exibirResultadoEspecifico");
//		mv.addObject("estab", est);
//		mv.addObject("result", listaComp);
//		return mv;
//	}
	
	
/*
 *  ################# Método não pode ser usado por ser estático ######################
 */
//	private static List<ExibirEstabelecimentoEvalorCompra> resultadoListaCompras(/*String... produtos*/) {
//		List <ExibirEstabelecimentoEvalorCompra> lev = new ArrayList<ExibirEstabelecimentoEvalorCompra>();
//		List <ListaComprasEstabelecimento> lce = new ArrayList<ListaComprasEstabelecimento>();
////		String[] cesta = produtos;
//		
//		for (Estabelecimento estab : er.findAll()) {
//			double soma = 0;
//			
//			for (Produto prod : pr.findByEstabelecimento(estab)) {
///*				for (int i = 0; i < cesta.length; i++) {
//					if(cesta[i].equalsIgnoreCase(prod.getNome())) {
//*/						lce.add(new ListaComprasEstabelecimento(prod.getId(), prod.getNome(), prod.getPreco()));
//						soma += Double.parseDouble(prod.getPreco().replace(",", "."));
///*					}
//				}
//*/			}
//			String somaTot = String.format("%.2f", soma);
//			lev.add(new ExibirEstabelecimentoEvalorCompra(estab.getId(), estab.getNome(), estab.getEndereco(), somaTot , lce));
//		}
//		return lev;
//	}

}
