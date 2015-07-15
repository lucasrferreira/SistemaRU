package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Curso;
import entidades.CursoFinder;
import entidades.Departamento;
import entidades.DepartamentoFinder;

public class GerirCurso
{
	public static Collection<Curso> listarCursos()
	{
		try
		{
			return CursoFinder._listarCursosDisponiveis();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Curso buscarCurso(String sigla) throws CursoNotFound
	{
		Curso curso = null;
		try
		{
			try
			{
				curso = CursoFinder._buscarCurso(sigla);
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NullPointerException e)
		{
			throw new CursoNotFound();
		}
		if (curso == null)
		{
			throw new CursoNotFound();
		}

		return curso;
	}

	public static void criarCurso(String sigla, String nome, String departamento) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, DepartamentoNotFound, ClassNotFoundException, SQLException
	{
		Departamento dpto = DepartamentoFinder._buscarDepartamento(departamento);

		if (dpto == null)
		{
			throw new DepartamentoNotFound();
		} else
		{
			Curso curso = new Curso(sigla, nome);

			if (CursoFinder._buscarCurso(sigla) == null)
			{
				if (curso.getSigla() == "")
				{
					throw new SiglaNotFoundException();
				} else
				{
					if (curso.getNome() == "")
					{
						throw new NomeNotFoundException();
					} else
					{
						curso._adicionarCurso();
					}
				}
			} else
			{
				throw new SiglaAlreadyExistsException(curso.getSigla());
			}
		}
	}

	public static void atualizarCurso(String sigla, String nome, String departamento) throws CursoNotFound, DepartamentoNotFound, ClassNotFoundException, SQLException, SiglaNotFoundException, NomeNotFoundException
	{
		Departamento dpto = DepartamentoFinder._buscarDepartamento(departamento);

		if (dpto == null)
		{
			throw new DepartamentoNotFound();
		} else
		{
			Curso curso = new Curso(sigla, nome);

			if (curso.getSigla() == "")
			{
				throw new SiglaNotFoundException();
			} else
			{
				if (curso.getNome() == "")
				{
					throw new NomeNotFoundException();
				} else
				{
					curso._adicionarCurso();
				}
			}
		}
	}
}
