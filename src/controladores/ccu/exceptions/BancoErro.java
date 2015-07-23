package controladores.ccu.exceptions;

public class BancoErro extends Exception {
		
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3194623518047730691L;

	public BancoErro(String erro)
	{
		super(erro);
	}
	
}
