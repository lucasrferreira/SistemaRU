package entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistencia.Conexao;
import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;

public class Curso implements Serializable
{
	private String			nome;
	private String			sigla;

	private Departamento	departamento;

	public Curso()
	{
	}

	public Curso load(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		sigla = rs.getString("sigla");
		nome = rs.getString("nome");

		Departamento departamento = DepartamentoFinder.get(rs.getString("departamento"));

		this.departamento = departamento;

		return this;
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

	// Domain Model

	public Collection<Curso> listarCursos() throws NenhumResultado, BancoErro, ClassNotFoundException, SQLException
	{
		Collection<Curso> colCurso = CursoFinder.getAll();
		if (colCurso.size() == 0)
		{
			throw new NenhumResultado("Banco vazio");
		}

		return CursoFinder.getAll();
	}

	public Curso buscarCurso(String sigla) throws CursoNotFound, ClassNotFoundException, SQLException
	{
		Curso curso = CursoFinder._buscarCurso(sigla);

		if (curso == null)
			throw new CursoNotFound("Curso não encontrado");

		return curso;
	}

	public void criarCurso(String sigla, String nome, String departamento) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, DepartamentoNotFound, ClassNotFoundException, SQLException
	{
		Departamento dpto = DepartamentoFinder.get(departamento);

		if (dpto == null)
			throw new DepartamentoNotFound();

		if (sigla == "")
			throw new SiglaNotFoundException("Preencha a sigla");
		if (nome == "")
			throw new NomeNotFoundException("Preencha o nome");

		if (CursoFinder._buscarCurso(sigla) != null)
			throw new SiglaAlreadyExistsException(sigla);

		this.sigla = sigla;
		this.nome = nome;
		this.departamento = dpto;

		this._adicionarCurso();

	}

	public void atualizarCurso(String sigla, String nome, String departamento) throws CursoNotFound, DepartamentoNotFound, ClassNotFoundException, SQLException, SiglaNotFoundException, NomeNotFoundException
	{

		Departamento dpto = DepartamentoFinder.get(departamento);

		if (dpto == null)
			throw new DepartamentoNotFound();

		if (sigla == "")
			throw new SiglaNotFoundException("Preencha a sigla");
		if (nome == "")
			throw new NomeNotFoundException("Preencha o nome");

		this.sigla = sigla;
		this.nome = nome;
		this.departamento = dpto;

		this._atualizarCurso();
	}

	public String getNome()
	{
		return nome;
	}

	public String getSigla()
	{
		return sigla;
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}

}
