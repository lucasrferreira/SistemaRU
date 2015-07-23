package controladores.aluno;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.exceptions.AnoIngressoNotFound;
import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.MatriculaNotFound;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SexoNotFound;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.TituloNotFound;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;
import entidades.Aluno;
import entidades.Curso;

@WebServlet("/CriarAluno")
public class CriarAluno extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
	Curso						curso				= new Curso();
	Aluno						aluno				= new Aluno();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoCriar");

		if (acao != null)
		{
			switch (acao)
			{
				case "Criar":
					criarAluno(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarConsumidor").forward(request, response);
			}
		} else
		{
			abrirForm(request, response);
		}
	}

	private void abrirForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			request.setAttribute("cursosDisponiveis", curso.listarCursos());
		} catch (ClassNotFoundException | NenhumResultado | BancoErro | SQLException e)
		{
			request.setAttribute("erro", "Não foi possivel encontrar cursos disponiveis");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("WEB-INF/aluno/CriarAluno.jsp").forward(request, response);
	}

	private void criarAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String nome = (String) request.getParameter("nome");
		int matricula = (Integer.parseInt(request.getParameter("matricula")));
		int ano = (Integer.parseInt(request.getParameter("anoIngresso")));
		String sexo = (String) request.getParameter("sexo");
		String cpf = (String) request.getParameter("cpf");
		String titulo = (String) request.getParameter("titulo");
		String curso = (String) request.getParameter("curso");

		try
		{
			aluno.criarAluno(nome, cpf, sexo, matricula, titulo, ano, curso);
			request.setAttribute("message", "Novo Aluno criado!");
			request.getRequestDispatcher("ListarConsumidor").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}