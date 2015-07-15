package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.Conexao;

public class Curso implements Serializable
{
	private String			nome;
	private String			sigla;

	private Departamento	departamento;

	public Curso(String sigla, String nome)
	{
		this.sigla = sigla;
		this.nome = nome;

		// TODO Auto-generated constructor stub
	}

	public Curso()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static Curso load(ResultSet rs) throws SQLException
	{
		Curso curso = new Curso();

		curso.setSigla(rs.getString("sigla"));
		curso.setNome(rs.getString("nome"));

		curso.setDepartamento(new Departamento());
		curso.getDepartamento().setSigla(rs.getString("departamento"));

		return curso;
	}

	public void _adicionarCurso() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Insert into curso (Sigla, nome, departamento) values (?, ?, ?);";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, sigla);
		pstmt.setString(2, nome);
		pstmt.setString(3, departamento.getSigla());

		pstmt.execute();

		Conexao.closeConnection();
	}



	public void _atualizarCurso() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Update curso set sigla = ?, nome =  ?, departamento = ? where sigla = ?;";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, sigla);
		pstmt.setString(2, nome);
		pstmt.setString(4, departamento.getSigla());

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

	public Departamento getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento)
	{
		this.departamento = departamento;
	}

}
