package controladores.ccu.exceptions;

public class NenhumResultado extends Exception
{
	String erro;
	
	public NenhumResultado(String erro)
	{
		this.erro = erro;
	}
}
