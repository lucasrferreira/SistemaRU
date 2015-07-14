package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import persistencia.DepartamentoService;
import entidades.value_objects.DepartamentoVO;

public class Departamento
{

	public static Collection<DepartamentoVO> _listarDepartamentosDisponiveis()
	{

		Collection<DepartamentoVO> colDpto = new ArrayList<DepartamentoVO>();
		ResultSet rs = null;
		try
		{

			DepartamentoService.initConnection();
			rs = DepartamentoService.listar();
			while (rs.next())
			{
				DepartamentoVO dpart = new DepartamentoVO();

				dpart.setSigla(rs.getString("sigla"));
				dpart.setNome(rs.getString("nome"));
				colDpto.add(dpart);
			}

			DepartamentoService.closeConnection();
		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Erro ao buscar os dados do banco");
			e.printStackTrace();
		}
		return colDpto;
	}

	// metodos de persistencia para Departamento

	public static void _adicionarDepartamento(DepartamentoVO dpto)
	{
		try
		{

			DepartamentoService.initConnection();
			if (DepartamentoService.insert(dpto))
				;
			DepartamentoService.closeConnection();

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Erro ao adicionar os dados no banco");
			e.printStackTrace();
		}

	}

	public static DepartamentoVO _buscarDepartamento(DepartamentoVO dp) throws ClassNotFoundException, SQLException
	{

		DepartamentoService.initConnection();
		ResultSet rs = DepartamentoService.busca(dp);

		while (rs.next())
		{
			dp.setSigla(rs.getString("sigla"));
			dp.setNome(rs.getString("nome"));
		}
		DepartamentoService.closeConnection();

		return dp;
	}

	public static void _atualizarDepartamento(HttpSession session, DepartamentoVO dpto) throws ClassNotFoundException, SQLException
	{
	
			DepartamentoService.initConnection();
			if (DepartamentoService.alterar(dpto));
			DepartamentoService.closeConnection();

	}
}
