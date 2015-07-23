package controladores.ccu.exceptions.nome;

public class NomeEmptyException extends Exception
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2617527970903708476L;
	
	public NomeEmptyException()
	{
		// TODO Auto-generated constructor stub
	}
	
	public NomeEmptyException(String message)
	{
		super(message);
	}
	
	NomeEmptyException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}

