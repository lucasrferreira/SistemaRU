package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Ticket;
import entidades.value_objects.TicketVO;

public class GerirTicket
{

	public static Collection<TicketVO> listarTickets() throws Exception
	{
		return Ticket._listarTicketsDisponiveis();
	}

	public static TicketVO buscarTicket(TicketVO ticketAntigo) throws Exception
	{

		ticketAntigo = Ticket._buscarTicket(ticketAntigo);

		return ticketAntigo;
	}

	public static void criarTicket(TicketVO dpto) throws Exception
	{

		if (Ticket._buscarTicket(dpto) != null)
		{
			Ticket._adicionarTicket(dpto);
			// retorno um ticket bobo }
		}
	}

	public static void atualizarTicket(TicketVO ticket) throws Exception
	{

		ticket = buscarTicket(ticket);

		Ticket._atualizarTicket(ticket);
	}
}
