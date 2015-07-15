package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirTicket;

@WebServlet("/CriarTicket")
public class CriarTicket extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoCriar");

		if (acao != null)
		{
			switch (acao)
			{
				case "Criar":
					criarTicket(request, response);
					break;
				case "Atualizar":
					request.getRequestDispatcher("AtualizarTicket").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarTicket").forward(request, response);
			}
		} else
		{
			request.getRequestDispatcher("WEB-INF/ticket/CriarTicket.jsp").forward(request, response);
		}
	}

	private void criarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		double valor = Double.parseDouble(request.getParameter("valor"));
		int refeicao = Integer.parseInt(request.getParameter("idRefeicao"));

		String consumidor = request.getParameter("cpf");

		try
		{
			GerirTicket.criarTicket(valor, consumidor, refeicao);
			request.setAttribute("message", "Novo ticket criado!");
			request.getRequestDispatcher("ListarTicket").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}