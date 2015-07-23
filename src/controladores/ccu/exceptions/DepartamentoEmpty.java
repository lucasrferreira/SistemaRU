package controladores.ccu.exceptions;

public class DepartamentoEmpty extends Exception{
	String erro;
	
	private String dpto;
	
	public DepartamentoEmpty(String dpto, String erro) {
		this.dpto = "";
		this.erro = erro;
	}
}