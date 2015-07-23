package controladores.ccu.exceptions;

import entidades.value_objects.CPF;

public class AnoIngressoEmpty extends Exception{
	
	String erro;
	
	private int ano;
	public AnoIngressoEmpty(int ano) {
		this.ano = 0;
	}
	public AnoIngressoEmpty(String string)
	{
		super(string);
	}
}