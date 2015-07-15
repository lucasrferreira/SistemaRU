package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;
import entidades.value_objects.TurnoVO;

public class RefeicaoFinder implements Serializable {
	private TurnoVO turno;
	private String descricao;
	private String op_veg;
	private int idRefeicao;
	

	public static Collection<Refeicao> _listarRefeicoesDisponiveis() throws ClassNotFoundException, SQLException {

		Collection<Refeicao> colRefeicao = new ArrayList<Refeicao>();
		ResultSet rs = null;

		Conexao.initConnection();

		String prepare = "Select * from refeicao;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			colRefeicao.add(Refeicao.load(rs));
		}

		Conexao.closeConnection();
		return colRefeicao;
	}



	public static Refeicao _buscarRefeicao(int idRefeicao) throws ClassNotFoundException, SQLException {
		Refeicao refeicao = null;
		
		Conexao.initConnection();

		ResultSet rs = null;

		Conexao.initConnection();

		String prepare = "Select * from refeicao where id= ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setInt(1, idRefeicao);
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			refeicao = 	Refeicao.load(rs);
		}

		Conexao.closeConnection();
	
		
		return refeicao;
		
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
}
