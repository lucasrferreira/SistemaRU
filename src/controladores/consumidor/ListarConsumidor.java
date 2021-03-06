package controladores.consumidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Aluno;
import entidades.Consumidor;
import entidades.Funcionario;

@WebServlet("/ListarConsumidor")
public class ListarConsumidor extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	Collection<Consumidor>		colConsumidor		= new ArrayList<Consumidor>();
	Funcionario					funcionario			= new Funcionario();
	Aluno						aluno				= new Aluno();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		listarConsumidores(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String acao = (String) request.getParameter("acaoListar");

		if (acao == null)
			acao = "";

		switch (acao)
		{
			case "Atualizar":
				request.getRequestDispatcher("AtualizarConsumidor").forward(request, response);
				break;
			case "Criar Aluno":
				request.getRequestDispatcher("CriarAluno").forward(request, response);
				break;
			case "Criar Funcionario":
				request.getRequestDispatcher("CriarFuncionario").forward(request, response);
				break;

			case "":
			default:
				listarConsumidores(request, response);
		}
	}

	private void listarConsumidores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		colConsumidor = new ArrayList<Consumidor>();
		try
		{
			colConsumidor.addAll(funcionario.listarFuncionario());
			colConsumidor.addAll(aluno.listarAluno());
			request.setAttribute("consumidores", colConsumidor);
		} catch (Exception e)
		{
			request.setAttribute("consumidores", new ArrayList<Consumidor>());
			e.printStackTrace();
		} finally
		{
			request.getRequestDispatcher("WEB-INF/consumidor/ListarConsumidor.jsp").forward(request, response);

		}
	}

}
