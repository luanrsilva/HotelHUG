package hotel;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Classe que representa uma abstracao de uma Transacao.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Transacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private double valor;
	private String tipo;

	public Transacao(String nome, double valor, String tipo) {

		this.nome = nome;
		this.valor = valor;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	private String formataValor(double valor) {
		String info = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		info += "R$" + df.format(valor);
		info = info.replace('.', ',');
		return info;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String FIM_DE_LINHA = System.lineSeparator();
		sb.append("==> Nome: " + this.getNome() + " Gasto: " + this.formataValor(this.getValor()) + " Detalhes: " + this.getTipo() + FIM_DE_LINHA);
		return sb.toString();
	}
}
