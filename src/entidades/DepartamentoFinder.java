package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;

public class DepartamentoFinder
{
	
	
	public static Collection<Departamento> _listarDepartamentosDisponiveis() throws ClassNotFoundException, SQLException
	{

		Collection<Departamento> colDpto = new ArrayList<Departamento>();
		ResultSet rs = null;

		Conexao.initConnection();

		String prepare = "Select * from departamento;";

		PreparedStatement psmt = Conexao.prepare(prepare);

		rs = psmt.executeQuery();
		
		while(rs.next()){
		
			colDpto.add(Departamento.load(rs));
		}
		
		rs.close();
		Conexao.closeConnection();

		return colDpto;
	}
	
	public static Departamento _buscarDepartamento(String sigla) throws ClassNotFoundException, SQLException
	{
		Departamento dp = null;

		Conexao.initConnection();
		String prepare = "Select * from departamento where sigla = ?;";

		PreparedStatement psmt = Conexao.prepare(prepare);

		psmt.setString(1, sigla);

		ResultSet rs = psmt.executeQuery();

		if (rs.next())
		{
			dp = Departamento.load(rs);
			return dp;
		}
		rs.close();
		Conexao.closeConnection();

		return null;
	}

}
