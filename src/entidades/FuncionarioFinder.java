package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;
import entidades.value_objects.CPF;

public class FuncionarioFinder
{

	public static Collection<Funcionario> _listarFuncionariosDisponiveis() throws Exception
	{
		Collection<Funcionario> colFunc = new ArrayList<Funcionario>();
		ResultSet rs = null;
		try
		{

			Conexao.initConnection();

			String prepare = "Select * from funcionario, consumidor where funcionario.cpf = consumidor.cpf;";

			PreparedStatement psmt = Conexao.prepare(prepare);
			rs = psmt.executeQuery();

			while (rs.next())
			{

				colFunc.add(Funcionario.load(rs));
			}

			Conexao.closeConnection();
		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Erro ao buscar os dados do banco");
			e.printStackTrace();
		}
		return colFunc;
	}

	public static Funcionario _buscarFuncionario(CPF cpf) throws Exception
	{
		Conexao.initConnection();
		ResultSet rs = null;
		
		String prepare = "Select * from funcionario, consumidor where funcionario.cpf = consumidor.cpf and consumidor.cpf = ?;";
		
		PreparedStatement psmt = Conexao.prepare(prepare);
		
		psmt.setString(1, cpf.toString());
		
		rs = psmt.executeQuery();
		
		if (rs.next())
		{
			return Funcionario.load(rs);

		}

		Conexao.closeConnection();

		return null;
	}

}
