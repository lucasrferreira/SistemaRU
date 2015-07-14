package entidades;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.DepartamentoService;
import persistencia.TicketService;
import entidades.value_objects.CPF;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TicketVO;

public class Ticket implements Serializable {
	// metodos de persistencia de TicketVO

	public static Collection<TicketVO> _listarTicketsDisponiveis() throws Exception {

		Collection<TicketVO> colTicket = new ArrayList<TicketVO>();
		ResultSet rs = null;

		TicketService.initConnection();
		rs = TicketService.listar();
		while (rs.next()) {
			TicketVO ticket = new TicketVO();

			ticket.setIdTicket(rs.getInt("id"));
			ticket.setValor(rs.getDouble("id"));

			ticket.setConsumidor(new ConsumidorVO());
			ticket.getConsumidor().setCpf(CPF.fromString(rs.getString("consumidor")));

			ticket.setRefeicao(new RefeicaoVO());
			ticket.getRefeicao().setIdRefeicao(rs.getInt("refeicao"));
				
			colTicket.add(ticket);
		}

		DepartamentoService.closeConnection();
		return colTicket;
	}

	public static void _adicionarTicket(TicketVO ticket) throws ClassNotFoundException, SQLException {

		TicketService.initConnection();
		TicketService.insert(ticket);
		TicketService.closeConnection();
	}

	public static TicketVO _buscarTicket(TicketVO ticket) throws Exception {
		
		TicketService.initConnection();
		ResultSet rs = TicketService.busca(ticket);
		
		if(rs.next())
		{
			ticket.setIdTicket(rs.getInt("id"));
			ticket.setValor(rs.getDouble("id"));

			ticket.setConsumidor(new ConsumidorVO());
			ticket.getConsumidor().setCpf(CPF.fromString(rs.getString("consumidor")));

			ticket.setRefeicao(new RefeicaoVO());
			ticket.getRefeicao().setIdRefeicao(rs.getInt("refeicao"));
			
			
		}
		
		TicketService.closeConnection();
		
		return ticket;
		
	}

	public static void _atualizarTicket(TicketVO ticket) throws ClassNotFoundException, SQLException 
	{

		TicketService.initConnection();
		TicketService.alterar(ticket);
		TicketService.closeConnection();
	}
}
