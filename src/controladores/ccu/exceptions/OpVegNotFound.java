package controladores.ccu.exceptions;

public class OpVegNotFound extends Exception
{
	String erro;
	
	public OpVegNotFound(String erro)
	{
		this.erro = erro;
	}
}
