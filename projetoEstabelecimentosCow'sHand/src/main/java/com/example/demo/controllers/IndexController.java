package com.example.demo.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.entities.Usuario;
import com.example.demo.security.Cripto;
import com.example.demo.security.LoginUsuario;

@Controller
public class IndexController {
	
	@Autowired
	EstabelecimentoController ec;
	
	@Autowired
	LoginUsuario lu;
	
/*
 * Esses métodos só direcionam para os arquivos "html" com a anotation "@Controller".
 * Com a anotation "@RestController é exibido no browser o que está escrito no "return".
 */
//	@RequestMapping("/")
//	public String index(){
//		return "paginas/index";
//	}
//	
//	@RequestMapping("/login")
//	public String login(){
//		return "paginas/login";
//	}
	
//	@RequestMapping("/")
//	public ModelAndView index(){
//		return new ModelAndView("paginas/index");
//	}
	
//	@RequestMapping("/login")
//	public ModelAndView login(){
//		return new ModelAndView("paginas/login");
//	}
	
	@GetMapping("/")
	public static ModelAndView index(){
		return new ModelAndView("paginas/index");
	}
	
	@GetMapping("/redirect")
	public static String redirecionar(){
		return "redirect:/login";
	}
	
	@GetMapping("/redirectAdmin")
	public String redirecionarAdmin(){
		if(lu.getUsuario().getAdministrador() != 0)
			return "redirect:/estabelecimento/lista";
		else {
		return "redirect:/";
		}
	}
	
	@GetMapping("/login")
	public static ModelAndView login(){
		ModelAndView mv = new ModelAndView("paginas/login");
		mv.addObject("user", new Usuario());
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView loginVerify(@Valid Usuario user, BindingResult br, HttpSession session) throws NoSuchAlgorithmException{
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new Usuario());
		
		if(br.hasErrors())
			mv.setViewName("paginas/login");
	
		Usuario userLog = lu.loginUser(user.getEmail(), Cripto.crypt(user.getSenha()));
		
		if(userLog == null) 
			mv.addObject("msg", "Email e/ou senha inválidos");
		else {
			session.setAttribute("usuarioLogado", userLog);
			return index();
		}
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return index();
	}

}

