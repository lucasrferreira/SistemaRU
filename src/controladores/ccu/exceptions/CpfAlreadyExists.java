package controladores.ccu.exceptions;

public class CpfAlreadyExists extends Exception
{
	private String cpf;

	public CpfAlreadyExists(String cpf) {
		this.cpf = cpf;
	}
}
