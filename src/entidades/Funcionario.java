package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistencia.Conexao;
import controladores.ccu.exceptions.AnoIngressoNotFound;
import controladores.ccu.exceptions.CpfAlreadyExists;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.MatriculaNotFound;
import controladores.ccu.exceptions.SexoNotFound;
import controladores.ccu.exceptions.nome.NomeEmptyException;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Funcionario extends Consumidor {
	
	private Departamento departamento;
	

	
	public void insert( ) throws ClassNotFoundException, SQLException 
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
		
		prepare = "Insert into funcionario ( cpf, departamento) values (?, ?);";
		
		pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, cpf.toString());
		pstmt.setString(2, departamento.getSigla());

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
		
		prepare = "Update funcinario set departamento = ? where cpf = ?;";
		
		pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, departamento.toString());
		pstmt.setString(2, cpf.toString());
		
		pstmt.execute();
		Conexao.closeConnection();
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}


	
	public Funcionario load(ResultSet rs) throws Exception
	{

		this.nome =  rs.getString("nome");
		this.matricula = rs.getInt("matricula");
		this.cpf = CPF.fromString(rs.getString("funcionario.cpf"));
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
		
		departamento = DepartamentoFinder.get(rs.getString("departamento"));
		
		return this;
		
	}
	
	
	//Domain Model
	
	
	public Collection<Funcionario> listarFuncionario() throws Exception
	{
		return FuncionarioFinder.getAll();
	}
	
	public void criarFuncionario(String nome, String cpf, String sexo, int matricula, String titulo, int ano, String departamento) throws Exception  {
		

		if (sexo.equals(Sexo.FEMININO.getSexo()))
			this.sexo = Sexo.FEMININO;
		if (sexo.equals(Sexo.MASCULINO.getSexo()))
			this.sexo = Sexo.MASCULINO;
		else
			throw new SexoNotFound("Sexo está incorreto");
		
		if (titulo.equals(Titulo.MESTRADO.getTitulo()))
			this.titulo = Titulo.MESTRADO;
		if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
			this.titulo = Titulo.DOUTORADO;
		if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
			this.titulo = Titulo.ESPECIALIZACAO;
		else
			//something

		if (nome == "")
			throw new NomeEmptyException();
		else
			this.nome = nome;
		if(sexo == "")
			throw new SexoNotFound("Sexo é campo obrigatorio");
		if(matricula == 0)//Trocar por valida matricula
			throw new MatriculaNotFound("Matricula é obrigatório");
		else
			this.matricula = matricula;
		if(ano == 0)//trocar pro valida ano
			throw new AnoIngressoNotFound("Ano é obrigatorio");
		else
			this.anoIngresso = ano;
		
		this.cpf = CPF.fromString(cpf);
		
		if (AlunoFinder.get(CPF.fromString(cpf)) == null)
			throw new CpfAlreadyExists("Ja existe um Consumidor com esse CPF");

		if (departamento != "")
			throw new DepartamentoNotFound();
		
		this.insert();
		
	}
	
	
}
