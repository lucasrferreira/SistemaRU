package controladores.ccu.exceptions;

public class ValorEmpty extends Exception{
	
	String erro;
	
	private double valor;
	
	public ValorEmpty(double valor) {
		this.valor = 0;
	}
	public ValorEmpty(String string)
	{
		super(string);
	}
}