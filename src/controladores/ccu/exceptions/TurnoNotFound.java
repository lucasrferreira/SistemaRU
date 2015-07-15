package controladores.ccu.exceptions;

public class TurnoNotFound extends Exception
{
	String erro;
	
	public TurnoNotFound(String erro)
	{
		this.erro = erro;
	}
}
