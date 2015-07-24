package controladores.ccu.exceptions;

public class NomeEmpty extends Exception{
	String erro;
	
	private String nome;
	
	public NomeEmpty(String nome, String erro) {
		this.nome = "";
		this.erro = erro;
	}
}