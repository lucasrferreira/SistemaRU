package controladores.ccu.exceptions;

public class MatriculaEmpty extends Exception{
	
	String erro;
	
	private int matricula;
	
	public MatriculaEmpty(int matricula) {
		this.matricula = 0;
	}
	public MatriculaEmpty(String string)
	{
		super(string);
	}
}