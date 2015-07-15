package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.Conexao;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Consumidor
{

	private String	nome;
	private int		matricula;
	private int		anoIngresso;
	private Sexo	sexo;
	private Titulo	titulo;
	protected CPF		cpf;

	public Consumidor()
	{
	}
	public Consumidor(String nome, int matricula, int ano)
	{
		this.anoIngresso = ano;
		this.matricula = matricula;
		this.nome = nome;
	}
	
	public void _adicionarConsumidor() throws SQLException, ClassNotFoundException
	{

		Conexao.initConnection();

		String prepare = "Insert into consumidor (nome, matricula, ano, sexo, titulo, cpf) value (?, ?, ? , ? , ?, ?);";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, nome);
		pstmt.setInt(2, matricula);
		pstmt.setInt(3, anoIngresso);
		pstmt.setString(4, sexo.getSexo());
		pstmt.setString(5, titulo.getTitulo());
		pstmt.setString(6, cpf.toString());

		pstmt.execute();

		Conexao.closeConnection();
	}

	public void _atualizarConsumidor() throws ClassNotFoundException, SQLException
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

	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public int getMatricula()
	{
		return matricula;
	}

	public void setMatricula(int matricula)
	{
		this.matricula = matricula;
	}

	public int getAnoIngresso()
	{
		return anoIngresso;
	}

	public void setAnoIngresso(int anoIngresso)
	{
		this.anoIngresso = anoIngresso;
	}

	public Sexo getSexo()
	{
		return sexo;
	}

	public void setSexo(Sexo sexo)
	{
		this.sexo = sexo;
	}

	public Titulo getTitulo()
	{
		return titulo;
	}

	public void setTitulo(Titulo titulo)
	{
		this.titulo = titulo;
	}

	public CPF getCpf()
	{
		return cpf;
	}

	public void setCpf(CPF cpf)
	{
		this.cpf = cpf;
	}

	public static Consumidor load(ResultSet rs) throws Exception
	{
		Consumidor consumidor = new Consumidor();

		consumidor.setNome(rs.getString("consumidor.nome"));
		consumidor.setMatricula(rs.getInt("consumidor.matricula"));
		consumidor.setCpf(CPF.fromString(rs.getString("consumidor.cpf")));
		consumidor.setAnoIngresso(rs.getInt("consumidor.ano"));

		if (rs.getString("consumidor.sexo").equals(Sexo.FEMININO.getSexo()))
			consumidor.setSexo(Sexo.FEMININO);
		if (rs.getString("consumidor.sexo").equals(Sexo.MASCULINO.getSexo()))
			consumidor.setSexo(Sexo.MASCULINO);

		if (rs.getString("consumidor.titulo").equals(Titulo.MESTRADO.getTitulo()))
			consumidor.setTitulo(Titulo.MESTRADO);
		if (rs.getString("consumidor.titulo").equals(Titulo.DOUTORADO.getTitulo()))
			consumidor.setTitulo(Titulo.DOUTORADO);
		if (rs.getString("consumidor.titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
			consumidor.setTitulo(Titulo.ESPECIALIZACAO);
		return consumidor;
	}

}
