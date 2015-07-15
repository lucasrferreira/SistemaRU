package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;

public class TicketFinder implements Serializable
{


	public static Collection<Ticket> _listarTicketsDisponiveis() throws Exception
	{

		Collection<Ticket> colTicket = new ArrayList<Ticket>();
		ResultSet rs = null;

		Conexao.initConnection();
		String prepare = "Select * from departamento;";
		PreparedStatement psmt = Conexao.prepare(prepare);
		
		rs = psmt.executeQuery();
		
		while (rs.next())
		{
			colTicket.add(Ticket.load(rs));
		}

		Conexao.closeConnection();
		return colTicket;
	}


	public static Ticket _buscarTicket(int idTicket) throws Exception
	{
		Ticket ticket = null;
		
		Conexao.initConnection();
		
		String prepare = "Select * from ticket where id = ?;";
		
		PreparedStatement psmt = Conexao.prepare(prepare);
		
		psmt.setInt(1, ticket.getIdTicket());
		ResultSet rs = psmt.executeQuery();

		if (rs.next())
		{
			ticket = Ticket.load(rs);
		}

		Conexao.closeConnection();

		return ticket;

	}

}
