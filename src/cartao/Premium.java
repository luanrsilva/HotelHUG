package cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que possui as definicoes de um cartao do tipo premium implementando a interface
 * TipoDeCartaoIF afim de utilizar o design patter strategy
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Premium implements TipoDeCartaoIF{
	private static final double DESCONTO_PREMIUM = 0.9;
	private static final double BONUS_PREMIUM = 0.3; 
	private static final double ADICIONAL_PREMIUM = 10;
	private static final double TAXA_CONVERTE_FIDELIDADE = 0.3;
	private static final double TAXA_CONVERTE_FIDELIDADE_BONUS = 0.2;
	
	/** 
	 * Metodo que retorna o valor do desconto de um cartao premium
	 */
	@Override
	public double desconto(double valorGasto) {
		double desconto = valorGasto * DESCONTO_PREMIUM;
		BigDecimal arredondado =  new BigDecimal(desconto).setScale(2, RoundingMode.HALF_UP);
		return arredondado.doubleValue();
	}

	/**
	 * Metodo que retorna o bonus referente ao tipo premium
	 */
	@Override
	public double bonus() {
		return BONUS_PREMIUM;
	}

	/**
	 * Metodo que retorna o adicional do desconto de um cartao premium
	 */
	@Override
	public double adicionalDesconto() {
		return 0;
	}

	/**
	 * Metodo que retorna o bonus adicional
	 * referente ao tipo premium
	 */
	@Override
	public double adicionalBonus() {
		return ADICIONAL_PREMIUM;
	}

	/**
	 * Metodo que define o valor final do gasto a partir do valor
	 * da taxa especifica do tipo padrao
	 */
	@Override
	public double pagaDividasGastos(double qtdPontos) {
		double valorConvertido = TAXA_CONVERTE_FIDELIDADE * qtdPontos;
		double adicional = ((int)(qtdPontos/10)) * TAXA_CONVERTE_FIDELIDADE_BONUS;
		double valorFinal = valorConvertido + adicional;
		return valorFinal;
	}

}
