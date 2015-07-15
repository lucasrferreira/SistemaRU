package controladores.ccu.exceptions;

public class SiglaNotFoundException extends Exception {
	String erro;
	
	public SiglaNotFoundException(String erro)
	{
		this.erro = erro;
	}
}
