package controladores.ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.AlunoFinder;
import entidades.Consumidor;
import entidades.FuncionarioFinder;
import entidades.Refeicao;
import entidades.Ticket;

@WebServlet("/CriarTicket")
public class CriarTicket extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
	Ticket ticket = new Ticket();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoCriar");

		if (acao != null)
		{
			switch (acao)
			{
				case "Calcular Preco":
					calcularPreco(request, response);
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
			loadForm(request, response);
		}
	}

	private void calcularPreco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
//		request.getRequestDispatcher("ConfirmarTicket").forward(request, response);

		
		String consumidor = request.getParameter("cpf");
		String turno = request.getParameter("turno");
		int refeicao = Integer.parseInt(request.getParameter("refeicao"));

	
		
		try
		{
			Double valor = ticket.calculaPreco(consumidor, turno);	
			request.setAttribute("preco", valor);
			
			Collection<Consumidor> colConsumidor = new ArrayList<Consumidor>();
			try
			{
				colConsumidor.addAll(AlunoFinder.getAll());
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try
			{
				colConsumidor.addAll(FuncionarioFinder.getAll());
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("colConsumidor", colConsumidor);
			try
			{
				request.setAttribute("colRefeicao", Refeicao.listarRefeicoes());
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.setAttribute("refeicao", refeicao);
			request.setAttribute("turno", turno);
			request.setAttribute("consumidor", consumidor);
			
			
			request.getRequestDispatcher("WEB-INF/ticket/CriarTicket.jsp").forward(request, response);

		} catch (Exception e)
		{
			request.getRequestDispatcher("WEB-INF/ticket/CriarTicket.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}

	private void loadForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Collection<Consumidor> colConsumidor = new ArrayList<Consumidor>();
		try
		{
			colConsumidor.addAll(AlunoFinder.getAll());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			colConsumidor.addAll(FuncionarioFinder.getAll());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("colConsumidor", colConsumidor);
		
		try
		{
			request.setAttribute("colRefeicao", Refeicao.listarRefeicoes());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("WEB-INF/ticket/CriarTicket.jsp").forward(request, response);
	}

	private void criarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		double valor = Double.parseDouble(request.getParameter("valor"));
		int refeicao = Integer.parseInt(request.getParameter("idRefeicao"));
		String consumidor = request.getParameter("cpf");
		String turno = request.getParameter("turno");
		boolean pago = Boolean.getBoolean(request.getParameter("pago"));

		try
		{
			ticket.criarTicket(valor, consumidor, refeicao, turno, pago);
			request.setAttribute("message", "Novo ticket criado!");
			request.getRequestDispatcher("ListarTicket").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}