package controladores.ccu.exceptions;

public class SexoNotFound extends Exception
{
	String erro;
	
	public SexoNotFound(String erro)
	{
		this.erro = erro;
	}
}
