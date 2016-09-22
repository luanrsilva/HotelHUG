package testes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.HotelController;
import exceptions.AtualizacaoHospedeException;
import exceptions.BuscaHospedeException;
import exceptions.CadastroException;
import exceptions.CadastroHospedeException;
import exceptions.ChecarHospedagemException;
import exceptions.CheckinException;
import exceptions.CheckoutException;
import exceptions.ConsultaException;
import exceptions.ConsultaHospedeException;
import exceptions.DadoInvalidoException;
import exceptions.HospedagemException;
import exceptions.RemocaoHospedeException;
import exceptions.StringInvalidaException;

public class HotelTest {

	private HotelController hotel;	
	
	@Before
	public void setUp() throws Exception {
		hotel = new HotelController();
		hotel.cadastraHospede("Luan Rocha", "luan@ccc.br", "29/07/1993");
		hotel.cadastraHospede("Kleber Insano", "insano@ccc.br", "31/12/1997");
		hotel.cadastraHospede("Ariann Farias", "ariann@farias.br", "25/05/1995");
	}
	
	
	@Test
	public void cadastraHospedeTest() throws StringInvalidaException, CadastroHospedeException{
		
		Assert.assertEquals("luan@ccc.br", hotel.cadastraHospede("Luan Rocha", "luan@ccc.br", "29/07/1993"));
		Assert.assertEquals("insano@ccc.br", hotel.cadastraHospede("Kleber Insano", "insano@ccc.br", "31/12/1997"));
		Assert.assertEquals("ariann@farias.br", hotel.cadastraHospede("Ariann Farias", "ariann@farias.br", "25/05/1995"));		
	}
	
	@Test
	public void removeHospedeTest() throws Exception{
		
		Assert.assertEquals("nilton@ccc.br", hotel.cadastraHospede("Nilton Ginani", "nilton@ccc.br", "20/10/1991"));
		Assert.assertTrue(hotel.removeHospede("nilton@ccc.br"));
	}
	
	public void infoHospedeTest() throws Exception{
		
		//Data de Nascimento
		Assert.assertEquals("25/05/1995", hotel.getInfoHospede("ariann@farias.br", "Data de Nascimento"));		
		
		//Email
		Assert.assertEquals("ariann@farias.br", hotel.getInfoHospede("ariann@farias.br", "Email"));
		
		//Nome
		Assert.assertEquals("Ariann Farias", hotel.getInfoHospede("ariann@ccc.br", "nome"));
	}
	
	@Test
	public void atualizaCadastroTest() throws Exception{
		
		//Atualiza Data de Nascimento
		Assert.assertEquals("25/05/1995", hotel.getInfoHospede("ariann@farias.br", "Data de Nascimento"));		
		hotel.atualizaCadastro("ariann@farias.br", "Data de Nascimento", "25/09/1995");		
		Assert.assertEquals("25/09/1995", hotel.getInfoHospede("ariann@farias.br", "Data de Nascimento"));
		
		//Atualiza Email
		Assert.assertEquals("ariann@farias.br", hotel.getInfoHospede("ariann@farias.br", "Email"));
		hotel.atualizaCadastro("ariann@farias.br", "email", "ariann@ccc.br");
		Assert.assertEquals("ariann@ccc.br", hotel.getInfoHospede("ariann@ccc.br", "email"));
		
		//Atualiza Nome
		Assert.assertEquals("Ariann Farias", hotel.getInfoHospede("ariann@ccc.br", "nome"));
		hotel.atualizaCadastro("ariann@ccc.br", "nome", "Ariann Borges");
		Assert.assertEquals("Ariann Borges", hotel.getInfoHospede("ariann@ccc.br", "nome"));
	}
	
	@Test
	public void hospedagemTransacaoTests() throws Exception{
		
		//Teste Checkin
		hotel.realizaCheckin("ariann@farias.br", 2, "5A", "Presidencial");
		hotel.realizaCheckin("luan@ccc.br", 4, "2A", "Simples");
		hotel.realizaCheckin("luan@ccc.br", 4, "3A", "Luxo");
		
		Assert.assertEquals("2", hotel.getInfoHospedagem("luan@ccc.br", "Hospedagens ativas"));
		Assert.assertEquals("2A,3A", hotel.getInfoHospedagem("luan@ccc.br", "Quarto"));
		Assert.assertEquals("R$1400,00", hotel.getInfoHospedagem("luan@ccc.br", "Total"));
		
		//Teste Checkout
		Assert.assertEquals("R$900,00", hotel.realizaCheckout("ariann@farias.br", "5A"));
		Assert.assertEquals("R$400,00", hotel.realizaCheckout("luan@ccc.br", "2A"));
		
		Assert.assertEquals("1", hotel.getInfoHospedagem("luan@ccc.br", "Hospedagens ativas"));
		Assert.assertEquals("3A", hotel.getInfoHospedagem("luan@ccc.br", "Quarto"));
		Assert.assertEquals("R$1000,00", hotel.getInfoHospedagem("luan@ccc.br", "Total"));
		
		Assert.assertEquals("R$1000,00", hotel.realizaCheckout("luan@ccc.br", "3A"));
		
		try{
			hotel.getInfoHospedagem("luan@ccc.br", "Hospedagens ativas");
			hotel.getInfoHospedagem("luan@ccc.br", "Quarto");
			hotel.getInfoHospedagem("luan@ccc.br", "Total");
		}catch(HospedagemException he){
			Assert.assertEquals("Erro na consulta de hospedagem. Hospede Luan Rocha nao esta hospedado(a).", he.getMessage());
		}			
		
		//Teste Transacoes
		Assert.assertEquals("3", hotel.consultaTransacoes("quantidade"));
		Assert.assertEquals("R$3700,00", hotel.consultaTransacoes("total"));
		Assert.assertEquals("Ariann Farias;Luan Rocha;Luan Rocha", hotel.consultaTransacoes("nome"));
		
		//Teste Transacoes passando indice
		Assert.assertEquals("R$900,00", hotel.consultaTransacoes("total", 0));
		Assert.assertEquals("Ariann Farias", hotel.consultaTransacoes("nome", 0));
		Assert.assertEquals("R$1400,00", hotel.consultaTransacoes("total", 1));
		Assert.assertEquals("Luan Rocha", hotel.consultaTransacoes("nome", 1));
	}
	
	//Testes das Exceptions
	@Test
	public void cadastraHospedeExceptions() throws StringInvalidaException{
		String nomeVazio = "Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.";
		String nomeInvalido = "Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.";
		String emailVazio = "Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.";
		String emailInvalido = "Erro no cadastro de Hospede. Email do(a) hospede esta invalido.";
		String dataVazia = "Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.";
		String dataInvalida = "Erro no cadastro de Hospede. Formato de data invalido.";
		String dataIdade = "Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.";
		
		//Exceptions de Nome
		try{
			hotel.cadastraHospede(" ", "niltonginani@ccc.br", "25/10/1992");
			fail();
		}catch (CadastroHospedeException ch){
			Assert.assertEquals(nomeVazio, ch.getMessage());
		}
		
		try{
			hotel.cadastraHospede("Nilton@Ginani", "niltonginani@ccc.br", "25/10/1992");
		}catch (CadastroHospedeException ch){
			Assert.assertEquals(nomeInvalido, ch.getMessage());
		}
		
		//Exceptions de Email
		try{
			hotel.cadastraHospede("Nilton Ginani", " ", "25/10/1992");
			fail();
		}catch (CadastroHospedeException ch){
			Assert.assertEquals(emailVazio, ch.getMessage());
		}
		
		try{
			hotel.cadastraHospede("Nilton Ginani", "nilton.ginani@ccc.br", "25/10/1992");
			fail();
		}catch (CadastroHospedeException ch){
			Assert.assertEquals(emailInvalido, ch.getMessage());
		}
		
		//Exceptions da Data de Nascimento
		try{
			hotel.cadastraHospede("Nilton Ginani", "niltonginani@ccc.br", " ");
			fail();
		}catch (CadastroHospedeException ch){
			Assert.assertEquals(dataVazia, ch.getMessage());
		}
		
		try{
			hotel.cadastraHospede("Nilton Ginani", "niltonginani@ccc.br", "25-10-1992");
			fail();
		}catch (CadastroHospedeException ch){
			Assert.assertEquals(dataInvalida, ch.getMessage());
		}
		
		try{
			hotel.cadastraHospede("Nilton Ginani", "niltonginani@ccc.br", "25/10/2000");
			fail();
		}catch (CadastroHospedeException ch){
			Assert.assertEquals(dataIdade, ch.getMessage());
		}
	}
}
