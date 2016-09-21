package testes;

import static org.junit.Assert.fail;
import hotel.Quarto;
import hotel.TipoQuarto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.IdInvalidoException;
import exceptions.StringInvalidaException;

public class QuartoTest {
	
	private Quarto quartoSimples;
	private Quarto quartoLuxo;
	private Quarto quartoPresidencial;

	@Before
	public void setUp() throws StringInvalidaException, IdInvalidoException {
		this.quartoSimples = new Quarto("2A", "simples");
		this.quartoLuxo = new Quarto("2B", "luxo");
		this.quartoPresidencial = new Quarto("2C", "presidencial");
	}
	
	@Test
	public void testDiaria(){
		Assert.assertEquals(100.00, quartoSimples.getDiaria(), 0.0);
		Assert.assertEquals(250.00, quartoLuxo.getDiaria(), 0.0);
		Assert.assertEquals(450.00, quartoPresidencial.getDiaria(), 0.0);
	}
	
	@Test
	public void testID(){
		Assert.assertEquals("2A", quartoSimples.getId());
		Assert.assertEquals("2B", quartoLuxo.getId());
		Assert.assertEquals("2C", quartoPresidencial.getId());
	}
	
	@Test
	public void testTipo(){
		Assert.assertEquals(TipoQuarto.LUXO, quartoLuxo.getTipo());
		Assert.assertEquals(TipoQuarto.SIMPLES, quartoSimples.getTipo());
		Assert.assertEquals(TipoQuarto.PRESIDENCIAL, quartoPresidencial.getTipo());
		
		Assert.assertFalse(TipoQuarto.LUXO.equals(quartoPresidencial.getTipo()));
	}
	
	@Test
	public void testEquals() {
		try{
			Quarto luxo2 = new Quarto("2B", "luxo");
			Quarto luxo3 = new Quarto("18B", "luxo");
			Quarto simples2 = new Quarto("2B", "simples");
			Quarto presidencial2 = new Quarto("30B", "presidencial");		
		
			Assert.assertTrue(this.quartoLuxo.equals(luxo2));
			
			Assert.assertFalse(this.quartoLuxo.equals(luxo3));
			Assert.assertFalse(this.quartoLuxo.equals(simples2));
			Assert.assertFalse(this.quartoSimples.equals(simples2));
		}catch (IdInvalidoException se){
			//Nao deve lancar esxception
		}
	}
}