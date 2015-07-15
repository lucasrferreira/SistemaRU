package controladores.refeicao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirRefeicao;
import controladores.ccu.exceptions.NomeNotFoundException;
//import controladores.ccu.exceptions.DescricaoNotFoundException;
//import controladores.ccu.exceptions.TurnoNotFoundException;
//import controladores.ccu.exceptions.OpVegNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.value_objects.TurnoVO;

@WebServlet("/CriarRefeicao")
public class CriarRefeicao extends HttpServlet
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
					criarRefeicao(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarRefeicao").forward(request, response);
			}
		} else
		{
			request.getRequestDispatcher("WEB-INF/refeicao/CriarRefeicao.jsp").forward(request, response);
		}
	}

	private void criarRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String descricao = (String) request.getParameter("descricao");
		String op_veg = (String) request.getParameter("op_veg");
		String turno = (String) request.getParameter("descricao");

		// try {
		try
		{
			GerirRefeicao.criarRefeicao( op_veg, descricao, turno);
			request.setAttribute("message", "Nova refeicao criada!");
			request.getRequestDispatcher("ListarRefeicao").forward(request, response);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SiglaNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NomeNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SiglaAlreadyExistsException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}