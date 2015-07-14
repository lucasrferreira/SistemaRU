package controladores.ccu;

import java.sql.SQLException;

import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Curso;
import entidades.Departamento;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class GerirCurso
{
	public static Object listarCursos()
	{
		try
		{
			return Curso._listarCursosDisponiveis();
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

	public static CursoVO buscarCurso(CursoVO cursoAntigo) throws CursoNotFound
	{
		try
		{
			try
			{
				cursoAntigo = Curso._buscarCurso(cursoAntigo);
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
		if (cursoAntigo == null)
		{
			throw new CursoNotFound();
		}

		return cursoAntigo;
	}

	public static void criarCurso(CursoVO curso) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, DepartamentoNotFound, ClassNotFoundException, SQLException
	{
		DepartamentoVO dpto = Departamento._buscarDepartamento(curso.getDepartamento());

		if (dpto == null)
		{
			throw new DepartamentoNotFound();
		} else
		{
			if (Curso._buscarCurso(curso) == null)
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
						Curso._adicionarCurso(curso);
					}
				}
			} else
			{
				throw new SiglaAlreadyExistsException(curso.getSigla());
			}
		}
	}

	public static void atualizarCurso(CursoVO cursoAntigo) throws CursoNotFound, DepartamentoNotFound, ClassNotFoundException, SQLException
	{
		DepartamentoVO dpto = Departamento._buscarDepartamento(cursoAntigo.getDepartamento());

		if (dpto == null)
		{
			throw new DepartamentoNotFound();
		} else
		{

			Curso._atualizarCurso(cursoAntigo);

		}
	}
}
