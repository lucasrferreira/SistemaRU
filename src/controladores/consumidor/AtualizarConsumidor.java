package controladores.consumidor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirConsumidor;

@WebServlet("/AtualizarConsumidor")
public class AtualizarConsumidor extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoCriar");

		if (acao != null)
		{
			switch (acao)
			{
				case "Atualizar":
					atualizarConsumidor(request, response);
					break;
				default:
					request.getRequestDispatcher("WEB-INF/consumidor/AtualizarConsumidor.jsp").forward(request, response);
			}
		} else
		{
			request.getRequestDispatcher("/ListarConsumidor").forward(request, response);
		}
	}

	private void atualizarConsumidor(HttpServletRequest request, HttpServletResponse response) 
	{

		String nome = (String) request.getParameter("nome");
		int matricula = (Integer.parseInt(request.getParameter("matricula")));
		int ano = (Integer.parseInt(request.getParameter("anoIngresso")));
		String sexo = (String) request.getParameter("sexo");
		String cpf = (String) request.getParameter("cpf");
		String titulo = (String) request.getParameter("titulo");

		try
		{
			GerirConsumidor.criarConsumidor(nome, cpf, sexo, matricula, titulo, ano);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarDepartamento").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}