package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;

public class CursoFinder implements Serializable
{

	public static Collection<Curso> getAll() throws ClassNotFoundException, SQLException
	{

		Collection<Curso> colCurso = new ArrayList<Curso>();
		ResultSet rs = null;

		Conexao.initConnection();

		String prepare = "Select * from curso;";

		PreparedStatement psmt = Conexao.prepare(prepare);
		rs = psmt.executeQuery();

		while (rs.next())
		{
			Curso curso = new Curso();
			colCurso.add(curso.load(rs));
		}

		Conexao.closeConnection();
		return colCurso;
	}


	public static Curso _buscarCurso(String sigla) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = null;
		Conexao.initConnection();
		Curso curso = null;
		
		String prepare = "Select * from curso where sigla = ?;";
		
		PreparedStatement psmt = Conexao.prepare(prepare);
		
		psmt.setString(1, sigla);
		rs = psmt.executeQuery();
		if (rs.next())
		{
			Curso _curso = new Curso();
			curso =  _curso.load(rs);
		}

		rs.close();
		Conexao.closeConnection();

		return curso;

	}
	


}
