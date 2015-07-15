package controladores.ccu.exceptions;

public class TituloNotFound extends Exception
{
	String erro;
	
	public TituloNotFound(String erro)
	{
		this.erro = erro;
	}
}
