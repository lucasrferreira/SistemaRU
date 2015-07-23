package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistencia.Conexao;
import controladores.ccu.exceptions.AnoIngressoEmpty;
import controladores.ccu.exceptions.CpfAlreadyExists;
import controladores.ccu.exceptions.CpfEmpty;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.MatriculaEmpty;
import controladores.ccu.exceptions.NomeEmpty;
import controladores.ccu.exceptions.SexoEmpty;
import controladores.ccu.exceptions.SexoNotFound;
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
		else if (sexo.equals(Sexo.MASCULINO.getSexo()))
			this.sexo = Sexo.MASCULINO;
		else
			throw new SexoNotFound("Sexo está incorreto");
		
		if (titulo.equals(Titulo.MESTRADO.getTitulo()))
			this.titulo = Titulo.MESTRADO;
		else if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
			this.titulo = Titulo.DOUTORADO;
		else if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
			this.titulo = Titulo.ESPECIALIZACAO;
		else
			throw new SexoNotFound("Título está incorreto");

		if (cpf == "")
			throw new CpfEmpty("", "CPF é obrigatório");
		else
			this.cpf = CPF.fromString(cpf);
		if (nome == "")
			throw new NomeEmpty("", "Nome é obrigatório");
		else
			this.nome = nome;
		if(sexo == "")
			throw new SexoEmpty("", "Sexo é obrigatório");
		if(matricula == 0)//Trocar por valida matricula
			throw new MatriculaEmpty("Matricula é obrigatório");
		else
			this.matricula = matricula;
		if(ano == 0)//trocar pro valida ano
			throw new AnoIngressoEmpty("Ano é obrigatorio");
		else
			this.anoIngresso = ano;
		
		
		
		try {
			if (AlunoFinder.get(CPF.fromString(cpf)).getCpf() != null)
				throw new CpfAlreadyExists("Ja existe um Consumidor com esse CPF");
		}
		catch ( Exception e)
		{
		}
		if (departamento == "")
			throw new DepartamentoNotFound();
		
		this.departamento = DepartamentoFinder.get(departamento);
		this.insert();
		
	}
	
	
}
