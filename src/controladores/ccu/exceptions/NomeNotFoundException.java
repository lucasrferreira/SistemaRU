package controladores.ccu.exceptions;

public class NomeNotFoundException extends Exception {
	String erro;
	
	public NomeNotFoundException(String erro)
	{
		this.erro = erro;
	}
}
