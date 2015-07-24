package controladores.ccu.exceptions;

import entidades.Consumidor;

public class ConsumidorEmpty extends Exception{
	
	String erro;
	
	private Consumidor consumidor;
	
	public ConsumidorEmpty(Consumidor consumidor) {
		this.consumidor = null;
	}
	public ConsumidorEmpty(String string)
	{
		super(string);
	}
}