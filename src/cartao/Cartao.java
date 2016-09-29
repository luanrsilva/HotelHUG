package cartao;

public class Cartao {
	
	private int pontos;
	private TipoCartao tipoCartao;
	
	public Cartao() {
		this.pontos = 0;
		this.setTipoCartao();
	}
	
	private void setTipoCartao(){
		if(pontos <= 350){
			this.tipoCartao = TipoCartao.PADRAO;
		}
		else if((pontos > 350) && (pontos <= 1000)){
			this.tipoCartao = TipoCartao.PREMIUM;
		}
		else if(pontos > 1000){
			this.tipoCartao = TipoCartao.VIP;
		}
	}
	
	public void adicionaPontos(double valorGasto){
		int pontos = 0;
		if(this.tipoCartao == TipoCartao.PADRAO){
			pontos += (int)((valorGasto * 10)/100);
		}
		else if(this.tipoCartao == TipoCartao.PREMIUM){
			int adicional = (int) ((valorGasto/100)*10);
			pontos += ((int)((valorGasto * 30)/100)) + adicional;
		}
		else if(this.tipoCartao == TipoCartao.VIP){
			pontos += (int)((valorGasto * 50)/100);
		}
		this.setTipoCartao(); //  tou chamando este método aqui, pois sempre que adicionar pontos, o tipo do cartao sera mudado automaticamente.
		this.setPontos(pontos);
	}
	
	
	public double aplicaDescontoGastos(double valorGasto){
		if(this.tipoCartao == TipoCartao.PADRAO){
			return valorGasto;
		}
		else if(this.tipoCartao == TipoCartao.PREMIUM){
			return valorGasto - ((valorGasto * 10)/100);
		}
		else if(this.tipoCartao == TipoCartao.VIP){
			int adicional = (int)(valorGasto/100)*10;
			return (valorGasto - ((valorGasto*15)/100)) - adicional;
		}
		return 0;
	}
	
	
	public double pagaDividasGastos(double valorGasto){ // FAZER ESTE MÉTODO
		return 0;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public TipoCartao getTipoCartao() {
		return tipoCartao;
	}
}