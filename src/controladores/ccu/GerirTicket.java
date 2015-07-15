package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.ConsumidorNotFound;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.RefeicaoFinder;
import entidades.Ticket;
import entidades.TicketFinder;

public class GerirTicket
{

	public static Collection<Ticket> listarTickets() throws Exception
	{
		try
		{
			Collection<Ticket> colTicket = TicketFinder._listarTicketsDisponiveis();
			if(colTicket.size() == 0)
			{
				throw new NenhumResultado("Banco vazio");
			}

		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			throw new BancoErro("Erro ao listar Tickets");
		}
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
		if (ticket.getValor() == 0){
			throw new ValorNotFound("Preencha o valor");
		}else{
			if (ticket.getRefeicao() == null){
				throw new RefeicaoNotFound("Preencha a refeicao");
			}else{
				if (ticket.getConsumidor() == null){
					throw new ConsumidorNotFound("Preencha o consumidor");
				}
		}
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
