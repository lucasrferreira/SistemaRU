package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirTicket;
import entidades.Ticket;

@WebServlet("/AtualizarTicket")
public class AtualizarTicket extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";
		switch (acao)
		{
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarTicket").forward(request, response);
				break;
			case "Atualizar":
				atualizarTicketAntigo(request, response);
				break;
			default:
				buscar(request, response);

		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response)
	{
		int idTicket = Integer.parseInt(request.getParameter("idTicket"));
		try
		{
			Ticket ticket = GerirTicket.buscarTicket(idTicket);
			request.setAttribute("ticket antigo", ticket);
			request.getRequestDispatcher("WEB-INF/ticket/AtualizarTicket.jsp").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void atualizarTicketAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idTicket = Integer.parseInt(request.getParameter("idTicket"));
		double valor = (Double.parseDouble(request.getParameter("valor")));
		int refeicao = Integer.parseInt(request.getParameter("refeicao"));
		String consumidor = request.getParameter("consumidor");

		try
		{
			GerirTicket.atualizarTicket(idTicket, valor, consumidor, refeicao);
			request.getRequestDispatcher("ListarDepartamento").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}