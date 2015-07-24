package entidades;

import org.junit.Test;

import controladores.ccu.exceptions.ConsumidorEmpty;
import controladores.ccu.exceptions.RefeicaoEmpty;
import controladores.ccu.exceptions.ValorEmpty;

public class TicketTest
{

	@Test(expected = ValorEmpty.class)
	public void testValorEmpty()
	{
		Ticket ticket = new Ticket();
		ticket.setValor(0);
	}
	
	@Test(expected = ConsumidorEmpty.class)
	public void testConsumidorEmpty()
	{
		Ticket ticket = new Ticket();
		ticket.setConsumidor(null);
	}
	
	@Test(expected = RefeicaoEmpty.class)
	public void testRefeicaoEmpty()
	{
		Ticket ticket = new Ticket();
		ticket.setRefeicao(null);
	}

}
