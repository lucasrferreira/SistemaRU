package controladores.ccu.exceptions.sigla;

public class SiglaAlreadyExistsException extends Exception
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2617527970903708476L;
	
	public SiglaAlreadyExistsException()
	{
		// TODO Auto-generated constructor stub
	}
	
	public SiglaAlreadyExistsException(String message)
	{
		super(message);
	}
	
	SiglaAlreadyExistsException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}

