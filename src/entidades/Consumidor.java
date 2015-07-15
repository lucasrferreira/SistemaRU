package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import persistencia.Conexao;
import persistencia.ConsumidorService;
import entidades.value_objects.CPF;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Consumidor
{

	private String nome;
	private int matricula;
	private int anoIngresso;
	private Sexo sexo;
	private Titulo titulo;
	private CPF cpf;
	
	public static Collection<ConsumidorVO> _listarConsumidoresDisponiveis() throws Exception
	{
		Collection<ConsumidorVO> colConsumidor = new ArrayList<ConsumidorVO>();
		ResultSet rs = null;

		ConsumidorService.initConnection();
		rs = ConsumidorService.listar();
		while (rs.next())
		{
			ConsumidorVO consumidor = new ConsumidorVO();

			consumidor.setNome(rs.getString("nome"));
			consumidor.setMatricula(rs.getInt("matricula"));
			consumidor.setCpf(CPF.fromString(rs.getString("cpf")));
			consumidor.setAnoIngresso(rs.getInt("ano"));

			if (rs.getString("sexo").equals(Sexo.FEMININO.getSexo()))
				consumidor.setSexo(Sexo.FEMININO);
			if (rs.getString("sexo").equals(Sexo.MASCULINO.getSexo()))
				consumidor.setSexo(Sexo.MASCULINO);

			if (rs.getString("titulo").equals(Titulo.MESTRADO.getTitulo()))
				consumidor.setTitulo(Titulo.MESTRADO);
			if (rs.getString("titulo").equals(Titulo.DOUTORADO.getTitulo()))
				consumidor.setTitulo(Titulo.DOUTORADO);
			if (rs.getString("titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
				consumidor.setTitulo(Titulo.ESPECIALIZACAO);

			colConsumidor.add(consumidor);
		}

		ConsumidorService.closeConnection();
		return colConsumidor;

	}

	public static void _adicionarConsumidor(ConsumidorVO curso) throws ClassNotFoundException, SQLException
	{

		ConsumidorService.initConnection();

		ConsumidorService.insert(curso);
		ConsumidorService.closeConnection();
	}

	public void _adicionarConsumidor(String nome, int matricula, CPF cpf, int anoIngresso, Sexo sexo, Titulo titulo) throws ClassNotFoundException, SQLException
	{

		ConsumidorService.initConnection();

		String prepare = "Insert into consumidor (nome, matricula, ano, sexo, titulo, cpf) value (?, ?, ? , ? , ?, ?);";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, nome);
		pstmt.setInt(2, matricula);
		pstmt.setInt(3, anoIngresso);
		pstmt.setString(4, sexo.getSexo());
		pstmt.setString(5, titulo.getTitulo());
		pstmt.setString(6, cpf.toString());

		pstmt.execute();

		ConsumidorService.closeConnection();
	}

	public Consumidor _buscarConsumidor(CPF cpf) throws SQLException, Exception
	{
		Consumidor consumidor;

		ConsumidorService.initConnection();
		
		String prepare = "Select * from consumidor where cpf = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setString(1, cpf.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
		{
			consumidor = new Consumidor();
			consumidor.setNome(rs.getString("nome"));
			consumidor.setMatricula(rs.getInt("matricula"));
			consumidor.setCpf(CPF.fromString(rs.getString("cpf")));
			consumidor.setAnoIngresso(rs.getInt("ano"));

			if (rs.getString("sexo").equals(Sexo.FEMININO.getSexo()))
				consumidor.setSexo(Sexo.FEMININO);
			if (rs.getString("sexo").equals(Sexo.MASCULINO.getSexo()))
				consumidor.setSexo(Sexo.MASCULINO);

			if (rs.getString("titulo").equals(Titulo.MESTRADO.getTitulo()))
				consumidor.setTitulo(Titulo.MESTRADO);
			if (rs.getString("titulo").equals(Titulo.DOUTORADO.getTitulo()))
				consumidor.setTitulo(Titulo.DOUTORADO);
			if (rs.getString("titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
				consumidor.setTitulo(Titulo.ESPECIALIZACAO);
			return consumidor;

		}

		ConsumidorService.closeConnection();
		return null;
	}

	public static void _atualizarConsumidor(ConsumidorVO consumidor) throws ClassNotFoundException, SQLException
	{
		ConsumidorService.initConnection();
		ConsumidorService.alterar(consumidor);
		ConsumidorService.closeConnection();

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

}
