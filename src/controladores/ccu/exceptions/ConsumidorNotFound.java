package controladores.ccu.exceptions;

public class ConsumidorNotFound extends Exception
{
	String erro;
	
	public ConsumidorNotFound(String erro)
	{
		this.erro = erro;
	}

}
