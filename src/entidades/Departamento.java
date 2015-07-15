package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;

public class Departamento
{
	private String	nome;
	private String	sigla;

	public Departamento(String nome, String sigla)
	{
		this.nome = nome;
		this.sigla = sigla;
	}

	public Departamento()
	{
	}

	public static Departamento load(ResultSet rs) throws SQLException
	{
		Departamento dpart = new Departamento();

		dpart.setSigla(rs.getString("sigla"));
		dpart.setNome(rs.getString("nome"));
		return dpart;
		
	}

	// metodos de persistencia para Departamento

	public void _adicionarDepartamento() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Insert into departamento (Sigla, nome) values (?, ?);";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, sigla);
		pstmt.setString(2, nome);

		pstmt.execute();

		Conexao.closeConnection();

	}



	public void _atualizarDepartamento() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Update departamento set sigla = ?, nome =  ? where sigla = ?;";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, sigla);
		pstmt.setString(2, nome);

		pstmt.setString(3, sigla);

		pstmt.execute();

		Conexao.closeConnection();

	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSigla()
	{
		return sigla;
	}

	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}

}
