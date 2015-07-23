package controladores.ccu.exceptions;

public class SexoEmpty extends Exception{
	
	String erro;
	
	private String sexo;
	
	public SexoEmpty(String sexo, String erro) {
		this.sexo = "";
		this.erro = erro;
	}
}