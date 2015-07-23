package controladores.ccu.exceptions;

import entidades.value_objects.CPF;

public class CpfEmpty extends Exception{
	
	String erro;
	
	private String cpf;
	
	public CpfEmpty(String cpf, String erro) {
		this.cpf = "";
		this.erro = erro;
	}

}