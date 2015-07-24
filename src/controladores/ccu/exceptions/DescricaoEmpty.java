package controladores.ccu.exceptions;

public class DescricaoEmpty extends Exception{
	
	String erro;
	
	private String descricao;
	
	public DescricaoEmpty(String descricao, String erro) {
		this.descricao = "";
		this.erro = erro;
	}

}