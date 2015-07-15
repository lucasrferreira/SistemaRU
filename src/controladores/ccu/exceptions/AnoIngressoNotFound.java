package controladores.ccu.exceptions;

public class AnoIngressoNotFound extends Exception{
	
	String erro;
	
	public AnoIngressoNotFound(String erro)
	{
		this.erro = erro;
	}
}
