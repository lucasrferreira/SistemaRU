package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.value_objects.CursoVO;

public class CursoService extends Conexao {

	
	public static Boolean insert(CursoVO curso) throws ClassNotFoundException, SQLException
	{
		String prepare = "Insert into curso (Sigla, nome, departamento) values (?, ?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setString(1, curso.getSigla());
		pstmt.setString(2, curso.getNome());
		pstmt.setString(3, curso.getDepartamento().getSigla());
		
		return execute();
		
	}
	public static ResultSet busca(CursoVO curso) throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from curso where sigla = ?;";
		
		Conexao.prepare(prepare);
		
		psmt.setString(1, curso.getSigla());
		
		return executeQuery();
		
	}
	
	public static Boolean alterar(CursoVO curso) throws ClassNotFoundException, SQLException
	{
		String prepare = "Update curso set sigla = ?, nome =  ?, departamento = ? where sigla = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setString(1, curso.getSigla());
		pstmt.setString(2, curso.getNome());
		pstmt.setString(4, curso.getDepartamento().getSigla());
		

		pstmt.setString(3, curso.getSigla());
		
		return execute();
		
	}
	
	public static ResultSet listar() throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from curso;";
		
		Conexao.prepare(prepare);
		return executeQuery();
		
	}
	
}

