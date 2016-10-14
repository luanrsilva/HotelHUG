package cartao;

/**
 * Classe que possui as definicoes de um cartao do tipo padrao implementando a interface
 * TipoDeCartaoIF afim de utilizar o design patter strategy
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Padrao implements TipoDeCartaoIF{
	private static final double DESCONTO_PADRAO = 1.0; // sem desconto
	private static final double BONUS_PADRAO = 0.1;
	private static final double TAXA_CONVERTE_FIDELIDADE = 0.1;
	
	/** 
	 * Metodo que retorna o valor do desconto de um cartao padrao
	 */
	@Override
	public double desconto(double valorgasto) {
		return valorgasto;
	}

	/**
	 * Metodo que retorna o bonus referente ao tipo padrao
	 */
	@Override
	public double bonus() {
		return BONUS_PADRAO;
	}

	/**
	 * Metodo que retorna o adicional do desconto de um cartao padrao
	 */
	@Override
	public double adicionalDesconto() {
		return 0;
	}

	/**
	 * Metodo que retorna o bonus adicional
	 * referente ao tipo padrao
	 */
	@Override
	public double adicionalBonus() {
		return 0;
	}
	
	/**
	 * Metodo que define o valor final do gasto a partir do valor
	 * da taxa especifica do tipo padrao
	 */
	@Override
	public double pagaDividasGastos(double qtdPontos) {
		return TAXA_CONVERTE_FIDELIDADE * qtdPontos;
	}

}
