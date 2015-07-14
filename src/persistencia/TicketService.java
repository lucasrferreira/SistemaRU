package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.value_objects.TicketVO;

public class TicketService extends Conexao {

	
	public static Boolean insert(TicketVO ticket) throws ClassNotFoundException, SQLException
	{
		
		String prepare = "Insert into ticket (valor, refeicao, consumidor) value (?, ?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setDouble(1, ticket.getValor());
		pstmt.setInt(2, ticket.getRefeicao().getIdRefeicao());
		pstmt.setString(3, ticket.getConsumidor().getCpf().toString());
		
		return execute();
		
	}
	public static ResultSet busca(TicketVO ticket) throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from ticket where id = ?;";
		
		Conexao.prepare(prepare);
		
		psmt.setInt(1, ticket.getIdTicket());
		
		return executeQuery();
		
	}
	
	public static Boolean alterar(TicketVO ticket) throws ClassNotFoundException, SQLException
	{
		String prepare = "Update ticket set valor = ?, refeicao =  ?, consumidor = ? where id = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setDouble(1, ticket.getValor());
		pstmt.setInt(2, ticket.getRefeicao().getIdRefeicao());
		pstmt.setString(2, ticket.getConsumidor().getCpf().toString());
		

		pstmt.setInt(3, ticket.getIdTicket());
		
		return execute();
		
	}
	
	public static ResultSet listar() throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from departamento;";
		
		Conexao.prepare(prepare);
		return executeQuery();
		
	}
	
}

