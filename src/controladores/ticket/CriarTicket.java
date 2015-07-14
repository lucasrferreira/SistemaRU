package controladores.ticket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TicketVO;




@WebServlet("/CriarDepartamento")
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
				default:
					request.getRequestDispatcher("ListarTicket").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/ticket/CriarTicket.jsp").forward(request,response);	
		}
	}

	private void criarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ticket = formToVo(request);
		
//		try {
			GerirTicket.criarTicket(ticket);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarDepartamento").forward(request,response);
//		} catch (RefeicaoNotFoundException | ValorNotFoundException | ConsumidorNotFoundException e2) {
//			request.setAttribute("erro", "Um ticket deve conter uma descricao, valor, consumdior");
//			request.getRequestDispatcher("WEB-INF/departamento/CriarDepartamento.jsp").forward(request,response);
//		}		
		
	}
	
	
	private TicketVO formToVo (HttpServletRequest request)
	{
		TicketVO ticket = new TicketVO();		
		
		ticket.setValor(Double.parseDouble(request.getParameter("valor")));		
		
		ticket.setRefeicao(new RefeicaoVO());
		ticket.getRefeicao().setIdRefeicao(Integer.parseInt(request.getParameter("idRefeicao")));
		
		//ticket.setRefeicao((String) request.getParameter("sigla"));
		
		return ticket;
	}
	
	private void voToForm (HttpServletRequest request, TicketVO dpto)
	{
		
		
	}

}