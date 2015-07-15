package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.Conexao;
import entidades.value_objects.CPF;

public class Ticket implements Serializable
{
	// metodos de persistencia de TicketVO
	private double		valor;
	private Refeicao	refeicao;
	private Consumidor	consumidor;
	private int			idTicket;

	public Ticket()
	{
	}

	public Ticket(double valor, Refeicao refeicao, Consumidor consumidor)
	{
		this.valor = valor;
		this.consumidor = consumidor;
		this.refeicao = refeicao;

	}

	public void _adicionarTicket() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Insert into ticket (valor, refeicao, consumidor) value (?, ?, ?);";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setDouble(1, valor);
		pstmt.setInt(2, refeicao.getIdRefeicao());
		pstmt.setString(3, consumidor.getCpf().toString());

		pstmt.execute();

		Conexao.closeConnection();
	}

	public void _atualizarTicket() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();
		String prepare = "Update ticket set valor = ?, refeicao =  ?, consumidor = ? where id = ?;";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setDouble(1, valor);
		pstmt.setInt(2, refeicao.getIdRefeicao());
		pstmt.setString(2, consumidor.getCpf().toString());

		pstmt.setInt(3, idTicket);

		Conexao.closeConnection();
	}

	public double getValor()
	{
		return valor;
	}

	public Consumidor getConsumidor()
	{
		return consumidor;
	}

	public Refeicao getRefeicao()
	{
		return refeicao;
	}

	public void setValor(double valor)
	{
		this.valor = valor;
	}

	public void setRefeicao(Refeicao refeicao)
	{
		this.refeicao = refeicao;
	}

	public void setConsumidor(Consumidor consumidor)
	{
		this.consumidor = consumidor;
	}

	public int getIdTicket()
	{
		return idTicket;
	}

	public void setIdTicket(int idTicket)
	{
		this.idTicket = idTicket;
	}

	public static Ticket load(ResultSet rs) throws Exception
	{
		Ticket ticket = new Ticket();

		ticket.setIdTicket(rs.getInt("id"));
		ticket.setValor(rs.getDouble("id"));

		ticket.setConsumidor(new Consumidor());
		ticket.getConsumidor().setCpf(CPF.fromString(rs.getString("consumidor")));

		ticket.setRefeicao(new Refeicao());
		ticket.getRefeicao().setIdRefeicao(rs.getInt("refeicao"));		
		
		return ticket;
	}
}
