package cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Classe que representa a abstracao de um cartao
 * 
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Cartao {
	/**
	 * Possui dois atributos, um para representar o valor dos pontos no cartao
	 * e o segundo é a interface responsavel por definir o tipo do cartao podendo ser modificado dinamicamente
	 */
	private int pontos;
	private TipoDeCartaoIF tipoCartao;
	
	/**
	 * Construtor da classe que inicializa o valor dos pontos igual a zero e
	 * o tipo do cartao como padrao
	 */
	public Cartao() {
		this.pontos = 0;
		this.tipoCartao = new Padrao();
	}
	
	/**
	 * Metodo que possui a finalidade de mudar dinamicamente o tipo de cartao a partir da quantidade de pontos
	 */
	private void setTipoCartao(){
		if(this.pontos < 350){
			this.tipoCartao = new Padrao();
		}
		else if((this.pontos >= 350) && (this.pontos <= 1000)){
			this.tipoCartao = new Premium();
		}
		else if(this.pontos > 1000){
			this.tipoCartao = new Vip();
		}
	}
	
	/**
	 * Adiciona pontos ao cartao por meio do valor gasto e tipo do bonus que o definido cartao possui
	 * desta forma ocorrendo uma chamada polimorfica
	 * @param valorGasto
	 */
	public void adicionaPontos(double valorGasto){
		int pontos = 0;
		int adicional = 0; 
 		if (valorGasto > 100) {
 			adicional += (int) ((int)(valorGasto/100) * tipoCartao.adicionalBonus());			
		}
		pontos += (valorGasto * tipoCartao.bonus()) + adicional;
		
		this.setPontos(this.getPontos() + pontos);
	}
	
	/**
	 * Metodo que retorna o valor do desconto por meio de uma chamada polimorfica
	 * @param valorGasto
	 * @return
	 */
	public double aplicaDescontoGastos(double valorGasto){
		
		double valorFinal = tipoCartao.desconto(valorGasto);		
		return valorFinal;
	}
	
	/**
	 * Metodo que retorna o valor pago pelo credito do cartao, utilizando de uma chamada polimorfica
	 * @param pontos
	 * @return
	 */
	public double pagaDividasGastos(double pontos){
		double valor = this.tipoCartao.pagaDividasGastos(pontos);
		return valor;
	}

	/**
	 * Metodo para visualizacao dos pontos pertencentes ao cartao
	 * @return
	 */
	public int getPontos() {
		return this.pontos;
	}

	/**
	 * Metodo que modifica o valor dos pontos do cartao, apos a modificacao, o metodo para modificar o tipo de cartao e chamado
	 * @param pontos
	 */
	public void setPontos(int pontos) {
		this.pontos = pontos;
		this.setTipoCartao();
	}
}