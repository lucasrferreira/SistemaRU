package entidades;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import persistencia.CursoService;
import persistencia.DepartamentoService;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class Curso implements Serializable
{
	// metodos de persistencia de CursoVO

	public static Collection<CursoVO> _listarCursosDisponiveis() throws ClassNotFoundException, SQLException
	{

		Collection<CursoVO> colCurso = new ArrayList<CursoVO>();
		ResultSet rs = null;

		CursoService.initConnection();
		rs = CursoService.listar();
		while (rs.next())
		{
			CursoVO curso = new CursoVO();

			curso.setSigla(rs.getString("sigla"));
			curso.setNome(rs.getString("nome"));

			curso.setDepartamento(new DepartamentoVO());
			curso.getDepartamento().setSigla(rs.getString("departamento"));

			colCurso.add(curso);
		}

		DepartamentoService.closeConnection();
		return colCurso;
	}

	public static void _adicionarCurso(CursoVO curso) throws ClassNotFoundException, SQLException
	{

		CursoService.initConnection();
		CursoService.insert(curso);
		CursoService.closeConnection();
	}

	public static CursoVO _buscarCurso(CursoVO curso) throws ClassNotFoundException, SQLException
	{

		CursoService.initConnection();
		ResultSet rs = CursoService.busca(curso);

		if (rs.next())
		{
			curso.setSigla(rs.getString("sigla"));
			curso.setNome(rs.getString("nome"));
			curso.setDepartamento(new DepartamentoVO());
			curso.getDepartamento().setSigla(rs.getString("departamento"));

		}

		CursoService.closeConnection();

		return curso;

	}

	public static void _atualizarCurso(CursoVO curso) throws ClassNotFoundException, SQLException
	{

		CursoService.initConnection();
		CursoService.alterar(curso);
		CursoService.closeConnection();
	}
}
