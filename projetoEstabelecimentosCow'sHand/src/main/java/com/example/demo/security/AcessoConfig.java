package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AcessoConfig implements WebMvcConfigurer{
	
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(new AcessoController()).addPathPatterns(new String[] {"/usuario/telas", "/usuario/produtos", 
				"/usuario/exibirResultados", "/estabelecimento/novo", "/estabelecimento/lista", "/estabelecimento/editar", 
				"/estabelecimento/deletarEstabelecimento", "/estabelecimento/deletarProduto", "/redirectAdmin"});
	}

}
