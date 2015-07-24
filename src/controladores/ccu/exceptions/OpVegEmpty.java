package controladores.ccu.exceptions;

public class OpVegEmpty extends Exception{
	
	String erro;
	
	private String op_veg;
	
	public OpVegEmpty(String op_veg, String erro) {
		this.op_veg = "";
		this.erro = erro;
	}

}