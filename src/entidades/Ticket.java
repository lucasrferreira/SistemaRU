package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistencia.Conexao;
import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.ValorNotFound;
import entidades.value_objects.TurnoVO;

public class Ticket implements Serializable
{
	// metodos de persistencia de TicketVO
	private double		valor;
	private Refeicao	refeicao;
	private Consumidor	consumidor;
	private TurnoVO		turno;
	private int			idTicket;
	private boolean		pago;

	public void _adicionarTicket() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Insert into ticket (valor, refeicao, consumidor) values (?, ?, ?);";

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
		String prepare = "Update ticket set pago = ? where id = ?;";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setBoolean(1, pago);

		pstmt.setInt(2, idTicket);

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

	public TurnoVO getTurno()
	{
		return turno;
	}

	public Refeicao getRefeicao()
	{
		return refeicao;
	}

	public int getIdTicket()
	{
		return idTicket;
	}

	public boolean isPago()
	{
		return pago;
	}

	public Ticket load(ResultSet rs) throws Exception
	{

		this.idTicket =  rs.getInt("id");
		this.valor = (rs.getDouble("valor"));
		
		Consumidor consumidor = new Aluno();
		
		this.consumidor = consumidor.buscaConsumidor(rs.getString("consumidor"));
		this.refeicao = RefeicaoFinder._buscarRefeicao(rs.getInt("refeicao"));
		
		String turno = rs.getString("turno");
		if (turno.equals(TurnoVO.MANHA.getTurno()))
			this.turno = TurnoVO.MANHA;
		if (turno.equals(TurnoVO.TARDE.getTurno()))
			this.turno = TurnoVO.TARDE;
		if (turno.equals(TurnoVO.NOITE.getTurno()))
			this.turno = TurnoVO.NOITE;
		
		pago = rs.getBoolean("pago");
		

		return this;
	}

	// Domain Model
	public Collection<Ticket> listarTickets() throws Exception
	{
		try
		{
			Collection<Ticket> colTicket = TicketFinder.getAll();
			if (colTicket.size() == 0)
			{
				throw new NenhumResultado("Banco vazio");
			}

		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			throw new BancoErro("Erro ao listar Tickets");
		}
		return TicketFinder.getAll();
	}

	public Ticket buscarTicket(int idTicket) throws Exception
	{

		return TicketFinder.get(idTicket);
	}

	public void criarTicket(double valor, String consumidor, int refeicao, String turno, boolean pago) throws Exception
	{


		Aluno aluno = new Aluno();
		this.consumidor = aluno.buscaConsumidor(consumidor);

		Refeicao _refeicao = new Refeicao();
		this.refeicao = _refeicao.buscarRefeicao(refeicao);

		if (turno.equals(TurnoVO.MANHA.getTurno()))
			this.turno = TurnoVO.MANHA;
		if (turno.equals(TurnoVO.TARDE.getTurno()))
			this.turno = TurnoVO.TARDE;
		if (turno.equals(TurnoVO.NOITE.getTurno()))
			this.turno = TurnoVO.NOITE;
		
		if ( this.consumidor instanceof Aluno){
			if( this.turno.getValorAluno() != valor )
			{
				throw new ValorNotFound("Valor está incorreto");
			}
		}
		else if ( this.consumidor instanceof Funcionario)
		{
			if( this.turno.getValorFuncionario() != valor )
			{
				throw new ValorNotFound("Valor está incorreto");
			}
		}
		
		this.pago = pago;
		this.valor = valor;

		this._adicionarTicket();
	}

	public void atualizarTicket(int idTicket, boolean pago) throws Exception
	{

		Ticket ticket = TicketFinder.get(idTicket);
		this.idTicket = ticket.getIdTicket();
		this.pago = pago;

		this._atualizarTicket();
	}
	
	public Double calculaPreco(String consumidor, String turno) throws Exception
	{
		Consumidor _consumidor = new Consumidor();
		
		TurnoVO _turno = TurnoVO.turnoVO(turno);
		
		_consumidor = _consumidor.buscaConsumidor(consumidor);

		if(_consumidor instanceof Aluno)
			return _turno.getValorAluno();
		if(_consumidor instanceof Funcionario)
			return _turno.getValorFuncionario();
		
//		throw new PrecoCalculoException("Não foi possivel calcular o preco");
		return null;
	}
}
