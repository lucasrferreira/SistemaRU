package controladores.ccu.exceptions;

public class CursoEmpty extends Exception{
	
	String erro;
	
	private String curso;
	
	public CursoEmpty(String curso, String erro) {
		this.curso = "";
		this.erro = erro;
	}

}