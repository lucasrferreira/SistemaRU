package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.Conexao;
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
		String prepare = "Insert into refeicao (descricao, turno, op_veg) value ( ?, ?, ?);";
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

	public void setTurno(TurnoVO turno)
	{
		this.turno = turno;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public String getOp_veg()
	{
		return op_veg;
	}

	public void setOp_veg(String op_veg)
	{
		this.op_veg = op_veg;
	}

	public int getIdRefeicao()
	{
		return idRefeicao;
	}

	public void setIdRefeicao(int idRefeicao)
	{
		this.idRefeicao = idRefeicao;
	}

	public static Refeicao load(ResultSet rs) throws SQLException
	{
		Refeicao refeicao = new Refeicao();

		refeicao.setIdRefeicao(rs.getInt("id"));
		refeicao.setDescricao(rs.getString("descricao"));
		refeicao.setOp_veg(rs.getString("op_veg"));

		if (rs.getString("turno").equals(TurnoVO.MANHA.getTurno()))
			refeicao.setTurno(TurnoVO.MANHA);
		if (rs.getString("turno").equals(TurnoVO.NOITE.getTurno()))
			refeicao.setTurno(TurnoVO.NOITE);
		if (rs.getString("turno").equals(TurnoVO.TARDE.getTurno()))
			refeicao.setTurno(TurnoVO.TARDE);

		return refeicao;
	}
}
