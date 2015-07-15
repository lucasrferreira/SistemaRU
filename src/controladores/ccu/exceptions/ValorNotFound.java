package controladores.ccu.exceptions;

public class ValorNotFound extends Exception
{
	
	String erro;
	
	public ValorNotFound(String erro)
	{
		this.erro = erro;
	}
}
