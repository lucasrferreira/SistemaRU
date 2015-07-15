package controladores.ccu.exceptions;

public class CpfAlreadyExists extends Exception
{
	private int cpf;

	public CpfAlreadyExists(int cpf) {
		this.cpf = cpf;
	}
}
