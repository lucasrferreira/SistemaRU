package controladores.ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirTicket;
import entidades.Aluno;
import entidades.AlunoFinder;
import entidades.Consumidor;
import entidades.FuncionarioFinder;
import entidades.RefeicaoFinder;

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
			loadForm(request, response);
		}
	}

	private void loadForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Collection<Consumidor> colConsumidor = new ArrayList<Consumidor>();
		try
		{
			colConsumidor.addAll(AlunoFinder._listarAlunosDisponiveis());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			colConsumidor.addAll(FuncionarioFinder._listarFuncionariosDisponiveis());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("colConsumidor", colConsumidor);
		try
		{
			request.setAttribute("colRefeicao", RefeicaoFinder._listarRefeicoesDisponiveis());
		} catch (ClassNotFoundException | SQLException e)
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