package controladores.curso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirCurso;
import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Curso;
import entidades.Departamento;

@WebServlet("/CriarCurso")
public class CriarCurso extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoCriar");
		Collection<Departamento> departamentoDisponiveis =
		null;
		try
		{
			departamentoDisponiveis = GerirDepartamento.listarDepartamentos();
			request.setAttribute("departamentosDisponiveis", departamentoDisponiveis);

		} catch (ClassNotFoundException | NenhumResultado | BancoErro | SQLException e)
		{
			request.setAttribute("departamentosDisponiveis", departamentoDisponiveis);
			request.getRequestDispatcher("WEB-INF/curso/CriarCurso.jsp").forward(request, response);
			e.printStackTrace();
		}

		if (acao != null)
		{
			switch (acao)
			{
				case "Criar":
					criarCurso(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarCurso").forward(request, response);
			}
		} else
		{
			request.getRequestDispatcher("WEB-INF/curso/CriarCurso.jsp").forward(request, response);
		}
	}

	private void criarCurso(HttpServletRequest request, HttpServletResponse response)
	{
		String nome = (String) request.getParameter("nome");
		String departamento = (String) request.getParameter("departamento");
		String sigla = (String) request.getParameter("sigla");

		try
		{
			GerirCurso.criarCurso(sigla, nome, departamento);
			request.setAttribute("message", "Novo departamento criado!");
			try
			{
				request.getRequestDispatcher("ListarCurso").forward(request, response);
			} catch (ServletException | IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DepartamentoNotFound e)
		{
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
			e.printStackTrace();
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		} catch (SiglaNotFoundException | NomeNotFoundException e2)
		{
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
		} catch (SiglaAlreadyExistsException e)
		{
			request.setAttribute("erro", "Sigla informada ja existe");

		}
		
		try
		{
			request.getRequestDispatcher("WEB-INF/curso/CriarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}