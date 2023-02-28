package teste.projeto.cm;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class Teste {

	@Test
	void test() {
		String s = "Casa ";
		String t = "!!!";
		String st = s + t;
		
		assertEquals("Casa !!!", st);
	}
	
	@Test
	void test02() {
		int i = 2 + 3 ;
		
		assertEquals(5, i);
	}

}
