package cartao;

public class Cartao {
	
	private int pontos;
	private TipoDeCartaoIF tipoCartao;
	
	public Cartao() {
		this.pontos = 0;
		this.tipoCartao = new Padrao();
	}
	
	public void setTipoCartao(){
		if(this.pontos <= 350){
			this.tipoCartao = new Padrao();
		}
		else if((this.pontos > 350) && (this.pontos <= 1000)){
			this.tipoCartao = new Premium();
		}
		else if(this.pontos > 1000){
			this.tipoCartao = new Vip();
		}
	}
	
	public void adicionaPontos(double valorGasto){
		int pontos = 0;
		int adicional = 0; 
 		if (valorGasto > 100) {
 			adicional += (int) ((valorGasto/100) * tipoCartao.adicionalBonus());			
		}
		pontos += (valorGasto * tipoCartao.bonus()) + adicional;
		
		this.setPontos(this.getPontos() + pontos);
		this.setTipoCartao(); //  chamando este metodo aqui, pois sempre que adicionar pontos, o tipo do cartao sera mudado automaticamente.
	}
	
	
	public double aplicaDescontoGastos(double valorGasto){
		int adicional = 0;
		int desconto = 0;
		
		if (valorGasto > 100) {			
			adicional = (int) ((valorGasto/100)* tipoCartao.adicionalDesconto());
		}
		
		return (valorGasto * tipoCartao.desconto() - adicional);
	}
	
	public double pagaDividasGastos(double pontos){
		double valor = this.tipoCartao.pagaDividasGastos(pontos);
		return valor;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
		this.setTipoCartao();
	}
}