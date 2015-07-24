package controladores.ccu.exceptions;

import entidades.Refeicao;

public class RefeicaoEmpty extends Exception{
	
	String erro;
	
	private Refeicao refeicao;
	
	public RefeicaoEmpty(Refeicao refeicao) {
		this.refeicao = null;
	}
	public RefeicaoEmpty(String string)
	{
		super(string);
	}
}