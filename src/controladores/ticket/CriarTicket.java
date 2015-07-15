package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirTicket;
import entidades.value_objects.CPF;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TicketVO;




@WebServlet("/CriarTicket")
public class CriarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TicketVO ticket;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarTicket(request,response);
					break;
				case "Atualizar":
					request.getRequestDispatcher("AtualizarTicket").forward(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarTicket").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/ticket/CriarTicket.jsp").forward(request,response);	
		}
	}

	private void criarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketVO ticket = new TicketVO();		
		
		ticket.setValor(Double.parseDouble(request.getParameter("valor")));		
		
		ticket.setRefeicao(new RefeicaoVO());
		ticket.getRefeicao().setIdRefeicao(Integer.parseInt(request.getParameter("idRefeicao")));
		
		ticket.setConsumidor(new ConsumidorVO());
		try
		{
			ticket.getConsumidor().setCpf(CPF.fromString("cpf"));
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
			try
			{
				GerirTicket.criarTicket(ticket);
				request.setAttribute("message", "Novo ticket criado!");
				request.getRequestDispatcher("ListarTicket").forward(request,response);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	
		
	}
	

}