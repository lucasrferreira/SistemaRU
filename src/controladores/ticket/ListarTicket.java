package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirTicket;


@WebServlet("/ListarTicket")
public class ListarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarTicket(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarTicket").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarTicket").forward(request,response);
				break;
			case "":
			default:
				listarTicket(request,response);				
		}
	}

	private void listarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			request.setAttribute("tickets", GerirTicket.listarTickets());
			request.getRequestDispatcher("WEB-INF/ticket/ListarTicket.jsp").forward(request,response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
