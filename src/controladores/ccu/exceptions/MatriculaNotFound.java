package controladores.ccu.exceptions;

public class MatriculaNotFound extends Exception
{
	String erro;
	
	public MatriculaNotFound(String erro)
	{
		this.erro = erro;
	}
}
