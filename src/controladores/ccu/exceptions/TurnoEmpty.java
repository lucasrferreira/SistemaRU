package controladores.ccu.exceptions;

public class TurnoEmpty extends Exception{
	
	String erro;
	
	private String turno;
	
	public TurnoEmpty(String turno, String erro) {
		this.turno = "";
		this.erro = erro;
	}

}