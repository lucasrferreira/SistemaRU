package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistencia.Conexao;
import controladores.ccu.exceptions.AnoIngressoEmpty;
import controladores.ccu.exceptions.CpfAlreadyExists;
import controladores.ccu.exceptions.CpfEmpty;
import controladores.ccu.exceptions.CursoEmpty;
import controladores.ccu.exceptions.MatriculaEmpty;
import controladores.ccu.exceptions.NomeEmpty;
import controladores.ccu.exceptions.SexoEmpty;
import controladores.ccu.exceptions.SexoNotFound;
import controladores.ccu.exceptions.TituloEmpty;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Aluno extends Consumidor{
	
	private Curso curso;
	


	public void insert() throws ClassNotFoundException, SQLException 
	{
		Conexao.initConnection();

		String prepare = "Insert into consumidor (nome, matricula, ano, sexo, titulo, cpf) values (?, ?, ? , ? , ?, ?);";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, nome);
		pstmt.setInt(2, matricula);
		pstmt.setInt(3, anoIngresso);
		pstmt.setString(4, sexo.getSexo());
		pstmt.setString(5, titulo.getTitulo());
		pstmt.setString(6, cpf.toString());

		pstmt.execute();

		Conexao.closeConnection();
		
		Conexao.initConnection();
		
		prepare = "Insert into aluno ( cpf, curso) values (?, ?);";
		
		pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, cpf.toString());
		pstmt.setString(2, curso.getSigla());
		
		pstmt.execute();
		Conexao.closeConnection();
	}


	public void update() throws SQLException, ClassNotFoundException 
	{
		Conexao.initConnection();

		String prepare = "Update consumidor set nome = ?, ano =  ?, matricula = ?, sexo = ?, titulo = ?  where cpf = ?;";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, nome);
		pstmt.setInt(2, anoIngresso);
		pstmt.setInt(3, matricula);
		pstmt.setString(4, sexo.getSexo());
		pstmt.setString(5, titulo.getTitulo());

		pstmt.setString(6, cpf.toString());

		Conexao.closeConnection();
		Conexao.initConnection();

		prepare = "Update aluno set curso = ? where cpf = ?;";
		
		pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, curso.getSigla());
		
		pstmt.setString(2, cpf.toString());
		pstmt.execute();
		
		Conexao.closeConnection();
	}
	
	public Aluno load(ResultSet rs) throws Exception
	{

		this.nome =  rs.getString("nome");
		this.matricula = rs.getInt("matricula");
		this.cpf = CPF.fromString(rs.getString("cpf"));
		this.anoIngresso = rs.getInt("ano");

		if (rs.getString("sexo").equals(Sexo.FEMININO.getSexo()))
			this.sexo = Sexo.FEMININO;
		if (rs.getString("sexo").equals(Sexo.MASCULINO.getSexo()))
			this.sexo = Sexo.MASCULINO;

		if (rs.getString("titulo").equals(Titulo.MESTRADO.getTitulo()))
			this.titulo = Titulo.MESTRADO;
		if (rs.getString("titulo").equals(Titulo.DOUTORADO.getTitulo()))
			this.titulo = Titulo.DOUTORADO;
		if (rs.getString("titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
			this.titulo = Titulo.ESPECIALIZACAO;
		;
		
		this.curso = CursoFinder._buscarCurso(rs.getString("curso"));
		
		return this;
	}

	public Curso getCurso()
	{
		return curso;
	}
	
	// Domain model

	public Collection<Aluno> listarAluno() throws Exception
	{
		
		
		return AlunoFinder.getAll();
	}
	
	public void criarAluno(String nome, String cpf, String sexo, int matricula, String titulo, int ano, String curso) throws Exception
	{

		
		if (sexo.equals(Sexo.FEMININO.getSexo()))
			this.sexo = Sexo.FEMININO;
		else if (sexo.equals(Sexo.MASCULINO.getSexo()))
			this.sexo = Sexo.MASCULINO;	
		else
			throw new SexoEmpty("", "Sexo est� incorreto");
		
		if(titulo =="")
			throw new TituloEmpty("", "");
		
		if (titulo.equals(Titulo.MESTRADO.getTitulo()))
			this.titulo = Titulo.MESTRADO;
		if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
			this.titulo = Titulo.DOUTORADO;
		if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
			this.titulo = Titulo.ESPECIALIZACAO;
		else
			//something

		if (nome == "")
			throw new NomeEmpty("", "Nome � obrigat�rio");
		else
			this.nome = nome;
		if(sexo == "")
			throw new SexoNotFound("Sexo � campo obrigatorio");
		if(matricula == 0)//Trocar por valida matricula
			throw new MatriculaEmpty("Matricula � obrigat�rio");
		else
			this.matricula = matricula;
		if(ano == 0)//trocar pro valida ano
			throw new AnoIngressoEmpty("Ano � obrigatorio");
		else
			this.anoIngresso = ano;
		if(curso == "")
			throw new CursoEmpty("", "Curso � obrigatorio");
		else
			this.curso = CursoFinder._buscarCurso(curso);
		
		if(cpf == "")
			throw new CpfEmpty("", "");
		
		this.cpf = CPF.fromString(cpf);
		
		if (AlunoFinder.get(CPF.fromString(cpf)) == null)
			throw new CpfAlreadyExists("Ja existe um Consumidor com esse CPF");
		
		this.insert();
		
		
		
	}


}
