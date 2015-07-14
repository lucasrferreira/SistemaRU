package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TicketVO;


@WebServlet("/AtualizarTicket")
public class AtualizarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarTicket").forward(request,response);
				break;
			case "Atualizar":
				atualizarTicketAntigo(request,response);
				break;
			default:				
				TicketVO ticketAntigo = GerirTicket.buscarTicket(request.getSession(),request.getParameter("id"));
				request.setAttribute("ticket antigo", ticketAntigo);
				request.getRequestDispatcher("WEB-INF/ticket/AtualizarTicket.jsp").forward(request,response);
						
		}
	}
	
	
	private void atualizarTAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String valor = (String.valueOf(request.getParameter("valor")));
		//RefeicaoVO refeicao = ()request.getParameter("refeicao");
		//ConsumidorVO consumidor = ()request.getParameter("consumidor");
		
		if (valor == "" || sigla == ""){
			request.setAttribute("erro", "Um ticket deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/departamento/AtualizarDepartamento.jsp").forward(request,response);
		}else{
			
				GerirTicket.atualizarDepartamento(request.getSession(), nome, sigla);
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				
		
		}
	}
}