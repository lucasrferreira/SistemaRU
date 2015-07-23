package controladores.ccu.exceptions.sigla;

public class SiglaEmptyException extends Exception
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2617527970903708476L;
	
	public SiglaEmptyException()
	{
		// TODO Auto-generated constructor stub
	}
	
	public SiglaEmptyException(String message)
	{
		super(message);
	}
	
	SiglaEmptyException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}

