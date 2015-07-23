package controladores.ccu.exceptions;

public class TituloEmpty extends Exception{
	String erro;
	
	private String titulo;
	
	public TituloEmpty(String titulo, String erro) {
		this.titulo = "";
		this.erro = erro;
	}
}