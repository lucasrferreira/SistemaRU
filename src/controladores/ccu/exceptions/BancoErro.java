package controladores.ccu.exceptions;

public class BancoErro extends Exception {
	
	String erro;
	
	public BancoErro(String erro)
	{
		this.erro = erro;
	}
	
}
