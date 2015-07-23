package controladores.ccu.exceptions;

import entidades.value_objects.CPF;

public class CpfAlreadyExists extends Exception
{
	private CPF cpf;
	public CpfAlreadyExists(CPF cpf) {
		this.cpf = cpf;
	}
	public CpfAlreadyExists(String string)
	{
		super(string);
	}
}
