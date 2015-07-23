package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import persistencia.Conexao;
import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.nome.NomeEmptyException;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.sigla.SiglaEmptyException;

public class Departamento
{
	private String	nome;
	private String	sigla;

//	public Departamento(String nome, String sigla)
//	{
//		this.nome = nome;
//		this.sigla = sigla;
//	}

	public Departamento()
	{
	}

	protected Departamento load(ResultSet rs) throws SQLException
	{
		
		this.sigla = rs.getString("sigla");
		this.nome = rs.getString("nome");
		return this;
		
	}

	// metodos de persistencia para Departamento

	protected void insert() throws ClassNotFoundException, SQLException
	{

		Conexao.initConnection();

		String prepare = "Insert into departamento (Sigla, nome) values (?, ?);";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, sigla);
		pstmt.setString(2, nome);

		pstmt.execute();

		Conexao.closeConnection();

	}



	protected void update() throws ClassNotFoundException, SQLException
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


	// Domain Model functions //

	public Collection<Departamento> listarDepartamentos() throws BancoErro, NenhumResultado, ClassNotFoundException, SQLException {

		try
		{
			Collection<Departamento> colDepartamento = DepartamentoFinder.getAll();
			if(colDepartamento.size() == 0)
			{
				throw new NenhumResultado("Banco vazio");
			}

		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();

			throw new BancoErro("Erro ao listar Departamentos");
		}
		return DepartamentoFinder.getAll();

	}
	
	public void criarDepartamento(String sigla, String nome) 
			throws SiglaAlreadyExistsException, SiglaEmptyException, NomeEmptyException, ClassNotFoundException, SQLException {
		
//		Departamento dpto = new Departamento(nome, sigla);
		
		if (sigla=="")
			throw new SiglaEmptyException("Preencha a sigla");
		
		if (nome=="")
			throw new NomeEmptyException("Preencha o nome");
		
		Departamento busca = DepartamentoFinder.get(sigla);	
		if (busca != null)
			throw new SiglaAlreadyExistsException(sigla);
					
		this.sigla = sigla;
		this.nome = nome;
		this.insert();
		
	}

	
	//Getters
	public String getNome()
	{
		return nome;
	}

	public String getSigla()
	{
		return sigla;
	}

	
	
	
}
