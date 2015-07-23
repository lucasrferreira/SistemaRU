package controladores.consumidor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Aluno;
import entidades.Consumidor;

@WebServlet("/AtualizarConsumidor")
public class AtualizarConsumidor extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
	private Consumidor			consumidor			= null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoAtualizar");

		if (acao == null)
			acao = "";
		
		switch (acao)
		{
			case "Atualizar":
				atualizarConsumidor(request, response);
				break;
			case "Voltar":
				request.getRequestDispatcher("ListarConsumidor").forward(request,response);
				break;
			default:
				busca(request, response);
		}
	}
	

	private void busca(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String cpf = (String) request.getParameter("cpf");
	
		consumidor = new Aluno();
		
		try
		{
			consumidor = consumidor.buscaConsumidor(cpf);
			request.setAttribute("consumidor", consumidor);
			request.getRequestDispatcher("WEB-INF/consumidor/AtualizarConsumidor.jsp").forward(request, response);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void atualizarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		consumidor = new Aluno();

		String nome = (String) request.getParameter("nome");
		String cpf = (String) request.getParameter("cpf");
		int matricula = (Integer.parseInt(request.getParameter("matricula")));
		int ano = (Integer.parseInt(request.getParameter("ano")));
		String sexo = (String) request.getParameter("sexo");

		try
		{
			consumidor.AtualizarConsumidor(cpf, nome, matricula, ano, sexo);
			request.getRequestDispatcher("ListarConsumidor").forward(request,response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}