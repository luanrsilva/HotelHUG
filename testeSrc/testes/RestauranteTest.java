package testes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.RestauranteController;
import exceptions.CadastraPratoException;
import exceptions.StringInvalidaException;

public class RestauranteTest {
	
	RestauranteController restaurante;

	@Before
	public void setUp() throws Exception {
		this.restaurante = new RestauranteController();
		restaurante.cadastraPrato("Frango empanado", 10.50, "Gostoso e crocante");
		restaurante.cadastraPrato("Bode guizado", 15.0, "Alimentatoda a familia");
		restaurante.cadastraPrato("Tapioca", 6.0, "Alimentatoda a familia");
		restaurante.cadastraRefeicao("Delicia da serra", "Uma selecao de pratos da culinaria regional nordestina.", "Tapioca;Bode guizado;Frango empanado");
	}
	
	@Test
	public void consultaRestauranteTest() throws Exception{
		Assert.assertEquals("R$10,50", restaurante.consultaRestaurante("Frango empanado", "preco"));
		Assert.assertEquals("R$15,00", restaurante.consultaRestaurante("Bode guizado", "preco"));
		Assert.assertEquals("R$28,35", restaurante.consultaRestaurante("Delicia da serra", "preco"));
		
		Assert.assertEquals("Gostoso e crocante", restaurante.consultaRestaurante("Frango empanado", "descricao"));
		Assert.assertEquals("Alimentatoda a familia", restaurante.consultaRestaurante("Bode guizado", "descricao"));
		Assert.assertEquals("Uma selecao de pratos da culinaria regional nordestina. Serao servidos: (1) Tapioca, (2) Bode guizado, (3) Frango empanado.", 
				restaurante.consultaRestaurante("Delicia da serra", "descricao"));
		
	}

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
