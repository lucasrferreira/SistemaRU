package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Ticket;

@WebServlet("/AtualizarTicket")
public class AtualizarTicket extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
	Ticket ticket = new Ticket();
	
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
			Ticket _ticket = ticket.buscarTicket(idTicket);
			request.setAttribute("ticket antigo", _ticket);
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
		boolean pago = Boolean.getBoolean(request.getParameter("pago"));

		try
		{
			ticket.atualizarTicket(idTicket, pago);
			request.getRequestDispatcher("ListarDepartamento").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}