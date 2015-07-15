package controladores.ccu.exceptions;

public class RefeicaoNotFound extends Exception{
	
	String erro;
	
	public RefeicaoNotFound(String erro)
	{
		this.erro = erro;
		}
}
