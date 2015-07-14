package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirTicket;
import entidades.value_objects.TicketVO;

@WebServlet("/ListarDepartamento")
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
		request.setAttribute("departamentos", GerirTicket.listarTicket());
		request.getRequestDispatcher("WEB-INF/ticket/ListarTicket.jsp").forward(request,response);
	}
	
	
	private TicketVO formToVo (HttpServletRequest request)
	{		
		
		return new TicketVO();
	}
	
	private void voToForm (HttpServletRequest request, TicketVO ticket)
	{
		
		
	}

}
