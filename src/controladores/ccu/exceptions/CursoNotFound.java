package controladores.ccu.exceptions;

public class CursoNotFound extends Exception {
	
	String erro;
	
	public CursoNotFound(String erro)
	{
		this.erro = erro;
	}
}
