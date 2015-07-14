package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.value_objects.DepartamentoVO;

public class DepartamentoService extends Conexao {

	
	public static Boolean insert(DepartamentoVO dpto) throws ClassNotFoundException, SQLException
	{
		String prepare = "Insert into departamento (Sigla, nome) values (?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setString(1, dpto.getSigla());
		pstmt.setString(2, dpto.getNome());
		
		return execute();
		
	}
	public static ResultSet busca(DepartamentoVO dpto) throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from departamento where sigla = ?;";
		
		Conexao.prepare(prepare);
		
		psmt.setString(1, dpto.getSigla());
		
		return executeQuery();
		
	}
	
	public static Boolean alterar(DepartamentoVO dpto) throws ClassNotFoundException, SQLException
	{
		String prepare = "Update departamento set sigla = ?, nome =  ? where sigla = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setString(1, dpto.getSigla());
		pstmt.setString(2, dpto.getNome());
		

		pstmt.setString(3, dpto.getSigla());
		
		return execute();
		
	}
	
	public static ResultSet listar() throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from departamento;";
		
		Conexao.prepare(prepare);
		return executeQuery();
		
	}
	
}

