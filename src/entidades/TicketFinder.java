package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;

public class TicketFinder implements Serializable
{


	public static Collection<Ticket> getAll() throws Exception
	{

		Collection<Ticket> colTicket = new ArrayList<Ticket>();
		ResultSet rs = null;

		Conexao.initConnection();
		String prepare = "Select * from departamento;";
		PreparedStatement psmt = Conexao.prepare(prepare);
		
		rs = psmt.executeQuery();
		
		while (rs.next())
		{
			Ticket _ticket = new Ticket();
			colTicket.add(_ticket.load(rs));
		}

		Conexao.closeConnection();
		return colTicket;
	}


	public static Ticket get(int idTicket) throws Exception
	{
		Ticket ticket = null;
		
		Conexao.initConnection();
		
		String prepare = "Select * from ticket where id = ?;";
		
		PreparedStatement psmt = Conexao.prepare(prepare);
		
		psmt.setInt(1, idTicket);
		ResultSet rs = psmt.executeQuery();

		if (rs.next())
		{
			Ticket _ticket = new Ticket();
			ticket = _ticket.load(rs);
		}

		Conexao.closeConnection();

		return ticket;

	}

}
