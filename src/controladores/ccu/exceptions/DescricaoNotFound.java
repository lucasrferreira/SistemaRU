package controladores.ccu.exceptions;

public class DescricaoNotFound extends Exception
{
	String erro;
	
	public DescricaoNotFound(String erro)
	{
		this.erro = erro;
	}
}
