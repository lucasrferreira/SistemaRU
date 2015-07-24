package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistencia.Conexao;
import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.DescricaoEmpty;
import controladores.ccu.exceptions.DescricaoNotFound;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.OpVegEmpty;
import controladores.ccu.exceptions.OpVegNotFound;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.TurnoEmpty;
import controladores.ccu.exceptions.TurnoNotFound;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;
import entidades.value_objects.TurnoVO;

public class Refeicao implements Serializable
{
	private TurnoVO	turno;
	private String	descricao;
	private String	op_veg;
	private int		idRefeicao;

	public Refeicao(int idRefeicao, String descricao, String op_veg)
	{

		this.descricao = descricao;
		this.idRefeicao = idRefeicao;
		this.op_veg = op_veg;
	}

	public Refeicao()
	{
		// TODO Auto-generated constructor stub
	}

	public Refeicao(String descricao, String op_veg)
	{
		this.descricao = descricao;
		this.op_veg = op_veg;
	}

	public void _adicionarRefeicao() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();
		String prepare = "Insert into refeicao (descricao, turno, op_veg) values ( ?, ?, ?);";
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, descricao);
		pstmt.setString(2, turno.getTurno());
		pstmt.setString(3, op_veg);

		pstmt.execute();

		Conexao.closeConnection();
	}



	public void _atualizarRefeicao() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Update refeicao set turno = ?, descricao = ?, op_veg = ? where id = ?;";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, turno.getTurno());
		pstmt.setString(2, descricao);
		pstmt.setString(3, op_veg);

		pstmt.setInt(4, idRefeicao);

		pstmt.execute();
		Conexao.closeConnection();
	}

	public TurnoVO getTurno()
	{
		return turno;
	}
	public String getDescricao()
	{
		return descricao;
	}

	public String getOp_veg()
	{
		return op_veg;
	}


	public int getIdRefeicao()
	{
		return idRefeicao;
	}

	public Refeicao load(ResultSet rs) throws SQLException
	{	
		this.idRefeicao = rs.getInt("id");
		this.descricao =  rs.getString("descricao");
		this.op_veg = rs.getString("op_veg");

		if (rs.getString("turno").equals(TurnoVO.MANHA.getTurno()))
			this.turno = TurnoVO.MANHA;
		if (rs.getString("turno").equals(TurnoVO.NOITE.getTurno()))
			this.turno = TurnoVO.NOITE;
		if (rs.getString("turno").equals(TurnoVO.TARDE.getTurno()))
			this.turno = TurnoVO.TARDE;

		return this;
	}
	
	
	

// Domain model 
	public static Collection<Refeicao> listarRefeicoes() throws Exception
	{
		try
		{
			Collection<Refeicao> colRefeicao = RefeicaoFinder._listarRefeicoesDisponiveis();
			if (colRefeicao.size() == 0)
			{
				throw new NenhumResultado("Banco vazio");
			}

		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			throw new BancoErro("Erro ao listar Refeicoes");
		}

		return RefeicaoFinder._listarRefeicoesDisponiveis();
	}

	public Refeicao buscarRefeicao(int idRefeicao) throws ClassNotFoundException, SQLException
	{
		Refeicao refeicaoAntigo = RefeicaoFinder._buscarRefeicao(idRefeicao);

		return refeicaoAntigo;
	}

	public void criarRefeicao(String op_veg, String descricao, String turno) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, 
	SQLException, DescricaoNotFound, OpVegNotFound, TurnoNotFound, DescricaoEmpty, OpVegEmpty, TurnoEmpty

	{

//		Refeicao refeicao = new Refeicao(descricao, op_veg);

		if (turno.equals(TurnoVO.MANHA.getTurno()))
			this.turno = TurnoVO.MANHA;
		else if (turno.equals(TurnoVO.NOITE.getTurno()))
			this.turno = TurnoVO.NOITE;
		else if (turno.equals(TurnoVO.TARDE.getTurno()))
			this.turno = TurnoVO.TARDE;
		else
			throw new TurnoEmpty("", "");

		if (descricao == "")
			throw new DescricaoEmpty("", "Preencha a descricao");
		if (op_veg == "")
			throw new OpVegEmpty("", "Prrencha a opcao vegetaliana");
		
		this._adicionarRefeicao();

	}

	public void atualizarRefeicao(int idRefeicao, String op_veg, String descricao, String turno) throws ClassNotFoundException, SQLException, DescricaoNotFound, OpVegNotFound, TurnoNotFound
	{
		if (turno.equals(TurnoVO.MANHA.getTurno()))
			this.turno = TurnoVO.MANHA;
		else if (turno.equals(TurnoVO.NOITE.getTurno()))
			this.turno = TurnoVO.NOITE;
		else if (turno.equals(TurnoVO.TARDE.getTurno()))
			this.turno = TurnoVO.TARDE;
		else
			//exception

		if (descricao == "")
			throw new DescricaoNotFound("Preencha a descricao");
		if (op_veg == "")
			throw new OpVegNotFound("Prrencha a opcao vegetaliana");
		
		this.idRefeicao = idRefeicao;
		
		this._atualizarRefeicao();
	}
}
