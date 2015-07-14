package entidades.value_objects;

import java.io.Serializable;

public class TicketVO implements Serializable {
	private double valor;
	private RefeicaoVO refeicao;
	private ConsumidorVO consumidor;
	private int idTicket;
	
	public TicketVO() { }

	public TicketVO(double valor, RefeicaoVO refeicao, ConsumidorVO consumidor){
		this.valor = valor;
		this.consumidor = consumidor;
		this.refeicao = refeicao;
		
	}
	
	
	public double getValor() {
		return valor;
	}
	
	public ConsumidorVO getConsumidor() {
		return consumidor;
	}
	
	public RefeicaoVO getRefeicao() {
		return refeicao;
	}

	public void setValor(double valor) {
		this.valor =  valor;
	}
	
	public void setRefeicao(RefeicaoVO refeicao) {
		this.refeicao =  refeicao;
	}
	
	public void setConsumidor(ConsumidorVO consumidor) {
		this.consumidor =  consumidor;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
}
