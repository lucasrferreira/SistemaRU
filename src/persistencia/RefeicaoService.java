package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.value_objects.RefeicaoVO;

public class RefeicaoService extends Conexao {

	
	public static Boolean insert(RefeicaoVO refeicao) throws ClassNotFoundException, SQLException
	{
		String prepare = "Insert into refeicao (descricao, turno, op_veg) value ( ?, ?, ?);";
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		
		pstmt.setString(1, refeicao.getDescricao());
		pstmt.setString(2, refeicao.getTurno());
		pstmt.setString(3, refeicao.getOpVeg());
		
		return execute();
		
	}
	public static ResultSet busca(RefeicaoVO refeicao) throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from refeicao where id= ?;";
		
		Conexao.prepare(prepare);
		
		psmt.setInt(1, refeicao.getIdRefeicao());
		
		return executeQuery();
		
	}
	
	public static Boolean alterar(RefeicaoVO refeicao) throws ClassNotFoundException, SQLException
	{
		String prepare = "Update refeicao set turno = ?, descricao = ?, op_veg = ? where id = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		psmt.setString(1, refeicao.getTurno());
		psmt.setString(2, refeicao.getDescricao());
		psmt.setString(3, refeicao.getOpVeg());
		
		psmt.setInt(4, refeicao.getIdRefeicao());
		
		return execute();
		
	}
	
	public static ResultSet listar() throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from refeicao;";
		
		Conexao.prepare(prepare);
		return executeQuery();
		
	}
	
}

