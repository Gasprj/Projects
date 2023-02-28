package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entities.Estabelecimento;
import com.example.demo.model.entities.Produto;
import com.example.demo.model.repositories.EstabelecimentoRepository;
import com.example.demo.model.repositories.ProdutosRepository;
import com.example.demo.security.LoginUsuario;

@Controller
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {
	
	@Autowired
	EstabelecimentoRepository er;
	
	@Autowired
	ProdutosRepository pr;
	
	@Autowired
	LoginUsuario lu;
	
	@GetMapping("/novo")
	public String form() {
		if(lu.getUsuario().getAdministrador() == -1)
			return "paginas/admin/formEstab";
		else 
			return "redirect:/estabelecimento/lista";
	}
	
	@PostMapping("/novo")
	public String formSave(Estabelecimento estab) {
		er.save(estab);
		return "redirect:/estabelecimento/lista";
	}
	
//	@GetMapping("/")
//	public String exibir() {
//		return "paginas/exibirEstab";
//	}
	
//	@RequestMapping("/lista") // Pode ser usado o "Get" ou o "Request"
	@GetMapping("/lista")
	public ModelAndView exibirLista() {
		ModelAndView mv = new ModelAndView("paginas/admin/exibirEstab");
		Iterable<Estabelecimento> estabs = er.findAll();
		mv.addObject("estabelecimentos", estabs);
		return mv;
	}
	
	@GetMapping(value ="/{id}")
	public ModelAndView inserirEexibirProdutos(@PathVariable("id") int id) {
		if(lu.getUsuario().getAdministrador() == id || lu.getUsuario().getAdministrador() == -1) {
			Estabelecimento estab = er.findById(id);
			ModelAndView mv = new ModelAndView("paginas/admin/produtos");
			mv.addObject("estabelecimento", estab);
			
			Iterable<Produto> prods = pr.findByEstabelecimento(estab);
			mv.addObject("produtos", prods);
			return mv;
		}else
			return this.exibirLista();	
	}
	
	@PostMapping(value ="/{id}")
	public String inserirEexibirProdutosSalvar(@PathVariable("id") int id, @Valid Produto prod
			, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
	        attributes.addFlashAttribute("mensagem", "Verifique os campos!");
	        return "redirect:/estabelecimento/{id}";
	    }
		Estabelecimento estab = er.findById(id);
	    prod.setEstabelecimento(estab);
	    pr.save(prod);
	    attributes.addFlashAttribute("mensagem", "Produto adicionado");
	    return "redirect:/estabelecimento/{id}";
	}
	
	@RequestMapping("/deletarEstabelecimento")
	public String deletarEstabelecimentoID(int id) {
		if(lu.getUsuario().getAdministrador() == -1) {
			Estabelecimento estab = er.findById(id);
			er.delete(estab);
		}
		return "redirect:/estabelecimento/lista";
    }

	@RequestMapping("/deletarProduto")
    public String deletarProdutoID(int id) {
        Produto prod = pr.findById(id);
        pr.delete(prod);
        Estabelecimento estab = prod.getEstabelecimento();
        String estabID = String.format("redirect:/estabelecimento/%d", estab.getId());
        return estabID ;  
    }
	
	@GetMapping("/editar")
	public ModelAndView editarEstab(int id) {
		if(lu.getUsuario().getAdministrador() == id || lu.getUsuario().getAdministrador() == -1) {
			Estabelecimento estab = er.findById(id);
			ModelAndView mv = new ModelAndView("paginas/admin/editarEstab");
			mv.addObject("estab", estab);
			return mv;
		}else
			return this.exibirLista();	
	}
	
	@PostMapping("/editar")
	public String editarEstabSalvar(Estabelecimento estab) {
		er.save(estab);
		return "redirect:/estabelecimento/lista";
	}
	
	@GetMapping(value ="/{id}/{id02}")
	public ModelAndView editarProdutoID(@PathVariable("id") int id, @PathVariable("id02") int id02) {
		Estabelecimento estab = er.findById(id);
		ModelAndView mv = new ModelAndView("paginas/admin/editarProduto");
		mv.addObject("estabelecimento", estab);
		
		Produto pro = pr.findById(id02);
		mv.addObject("produto", pro);
		return mv;
	}
	
	@PostMapping(value ="/{id}/{id02}")
	public String editarProdutoIDSalvar(@PathVariable("id") int id, @PathVariable("id02") int id02, @Valid Produto prod
			, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
	        attributes.addFlashAttribute("mensagem", "Verifique os campos!");
	        return "redirect:/estabelecimento/{id}";
	    }
		Estabelecimento estab = er.findById(id);
	    prod.setEstabelecimento(estab);
	    prod.setId(id02);
	    pr.save(prod);
	    attributes.addFlashAttribute("mensagem", "Produto adicionado");
	    return "redirect:/estabelecimento/{id}";
	}
	
}
