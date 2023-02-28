package teste.projeto.cm;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
/*
 *	O uso do * importa tudo
 */
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projeto.campoMinado.excessao.ExplosaoException;
import projeto.campoMinado.modelo.Campo;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach // Executa essa função antes de executar cada método.
	private void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeNaoVizinho01() {
		Campo vizinho = new Campo(4, 5);
		boolean resut = this.campo.adicionarVizinho(vizinho);
		
		assertFalse(resut);
	}
	
	@Test
	void testeNaoVizinho02() {
		Campo vizinho = new Campo(3, 5);
		boolean resut = this.campo.adicionarVizinho(vizinho);
		
		assertFalse(resut);
	}
	
	@Test
	void testeVizinhoEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resut = this.campo.adicionarVizinho(vizinho);
		
		assertTrue(resut);
	}
	
	@Test
	void testeVizinhoDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resut = this.campo.adicionarVizinho(vizinho);
		
		assertTrue(resut);
	}
	
	@Test
	void testeVizinhoCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resut = this.campo.adicionarVizinho(vizinho);
		
		assertTrue(resut);
	}
	
	@Test
	void testeVizinhoBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resut = this.campo.adicionarVizinho(vizinho);
		
		assertTrue(resut);
	}

	@Test
	void testarMarcacaoPadrao() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testarMarcacaoAlternar() {
		campo.marcador();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testarMarcacaoAlternar02X() {
		campo.marcador();
		campo.marcador();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeMinarCampoMinado() {
		campo.minarCampo();
		assertFalse(campo.minarCampo());
	}
	
	@Test
	void testeVerificarCampoMinado01() {
		assertFalse(campo.isMinado());
	}
	
	@Test
	void testeVerificarCampoMinado02() {
		campo.minarCampo();
		assertTrue(campo.isMinado());
	}
	
	@Test
	void abrirCampoNaoMarcadoNaoMinado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void abrirCampoNaoMinadoMarcado() {
		campo.marcador();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirCampoMarcadoMinado() {
		campo.marcador();
		campo.minarCampo();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirCampoNaoMarcadoMinado() {
		campo.minarCampo();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirComVizinhos01() {
		Campo campo01 = new Campo(1, 1);
		Campo campo02 = new Campo(2, 2);
		Campo campo03 = new Campo(1, 2);
		
		campo02.adicionarVizinho(campo01);
		campo01.adicionarVizinho(campo03);
		
		this.campo.adicionarVizinho(campo02);		
		
		campo.abrir();
		
		assertTrue(campo01.isAberto() && campo02.isAberto() && campo03.isAberto());
	}
	
	@Test
	void abrirComVizinhos02() {
		Campo campo01 = new Campo(1, 1);
		Campo campo02 = new Campo(2, 2);
		Campo campo03 = new Campo(1, 2);
		campo03.minarCampo();
		
		campo02.adicionarVizinho(campo01);
		campo01.adicionarVizinho(campo03);
		
		this.campo.adicionarVizinho(campo02);		
		
		campo.abrir();
		
		assertTrue(campo01.isAberto() && campo02.isAberto() && !campo03.isAberto());
	}
	
	@Test
	void abrirComVizinhos03() {
		Campo campo01 = new Campo(1, 1);
		Campo campo02 = new Campo(2, 2);
		Campo campo03 = new Campo(1, 2);
		Campo campo04 = new Campo(2, 1);
		campo01.marcador();
		
		campo02.adicionarVizinho(campo03);
		campo03.adicionarVizinho(campo01);
		campo03.adicionarVizinho(campo04);
		this.campo.adicionarVizinho(campo02);		
		campo.abrir();
		
		assertTrue(!campo01.isAberto() && campo02.isAberto() && campo03.isAberto() && !campo04.isAberto());
	}

	@Test
	void testeObjetivo01() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivo02() {
		campo.minarCampo();
		campo.marcador();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeReiniciar() {
		campo.reiniciar();
		assertFalse(campo.isMarcado() && campo.isAberto() && campo.isMinado());
	}
	
//	void testeToString01() {
//		campo.marcador();
//		assertNotNull(campo.toString(), "m");
//	}
	
//	void testeMinasAoRedor() {
//		Campo campo01 = new Campo(1, 1);
//		Campo campo02 = new Campo(2, 2);
//		Campo campo03 = new Campo(1, 2);
//		campo01.minarCampo();
//		campo02.minarCampo();
//		campo03.minarCampo();
//		
//		campo02.adicionarVizinho(campo01);
//		campo01.adicionarVizinho(campo03);
//		this.campo.adicionarVizinho(campo02);	
//		this.campo.abrir();
//		
//		assertEquals(1, campo.minasAoRedor());
//	}
	
}
