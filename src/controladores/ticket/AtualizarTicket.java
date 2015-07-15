package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirTicket;


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
				
				try
				{
					TicketVO ticketAntigo =	null;
					ticketAntigo = GerirTicket.buscarTicket(ticketAntigo);
					request.setAttribute("ticket antigo", ticketAntigo);
					request.getRequestDispatcher("WEB-INF/ticket/AtualizarTicket.jsp").forward(request,response);
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

						
		}
	}
	
	
	private void atualizarTicketAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double valor = (Double.parseDouble(request.getParameter("valor")));
		//duvida em como pegar
		RefeicaoVO refeicao =  ;
		

		
		

		
		
		//RefeicaoVO refeicao = ()request.getParameter("refeicao");
		//ConsumidorVO consumidor = ()request.getParameter("consumidor");
		
		if (valor == 0 || refeicao == "" || consumidor = ""){
			request.setAttribute("erro", "Um ticket deve conter um valor, refeicao e um consumidor");
			request.getRequestDispatcher("WEB-INF/departamento/AtualizarTicket.jsp").forward(request,response);
		}else{
			
				GerirTicket.atualizarDepartamento(request.getSession(), nome, sigla);
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				
		
		}
	}
}