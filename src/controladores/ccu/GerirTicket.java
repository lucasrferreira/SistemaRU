package controladores.ccu;

import java.util.Collection;

import entidades.Consumidor;
import entidades.Refeicao;
import entidades.RefeicaoFinder;
import entidades.Ticket;
import entidades.TicketFinder;

public class GerirTicket
{

	public static Collection<Ticket> listarTickets() throws Exception
	{
		return TicketFinder._listarTicketsDisponiveis();
	}

	public static Ticket buscarTicket(int idTicket) throws Exception
	{

		Ticket ticketAntigo = TicketFinder._buscarTicket(idTicket);

		return ticketAntigo;
	}

	public static void criarTicket(double valor, String consumidor, int refeicao) throws Exception
	{
		Ticket ticket = new Ticket();

		ticket.setValor(valor);

		ticket.setRefeicao(RefeicaoFinder._buscarRefeicao(refeicao));
		// ticket.setConsumidor(ConsumidorFinder._buscarConsumidor(consumidor));
		ticket._adicionarTicket();
	}

	public static void atualizarTicket(int idRefeicao ,double valor, String consumidor, int refeicao) throws Exception
	{

		Ticket ticket = buscarTicket(idRefeicao);

		ticket.setRefeicao(RefeicaoFinder._buscarRefeicao(refeicao));
		//msm coisa com consumidor
		
		ticket._atualizarTicket();
	}
}
