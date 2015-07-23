package controladores.refeicao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.exceptions.DescricaoNotFound;
import controladores.ccu.exceptions.OpVegNotFound;
import controladores.ccu.exceptions.TurnoNotFound;
import entidades.Refeicao;

@WebServlet("/AtualizarRefeicao")
public class AtualizarRefeicao extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	
	Refeicao refeicao = new Refeicao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao)
		{
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarRefeicao").forward(request, response);
				break;
			case "Atualizar":
				atualizarRefeicaoAntiga(request, response);
				break;
			default:
				buscar(request, response);
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idRefeicao = Integer.parseInt(request.getParameter("idRefeicao"));
		try
		{
			Refeicao refeicaoAntiga = refeicao.buscarRefeicao(idRefeicao);
			request.setAttribute("refeicao", refeicaoAntiga);
			request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e2)
		{
			request.setAttribute("erro", "A refeicao informada nao existe");
			request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request, response);
		}

	}

	private void atualizarRefeicaoAntiga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String descricao = (String) request.getParameter("descricao");
		String op_veg = (String) request.getParameter("op_veg");
		String turno = (String) request.getParameter("descricao");
		int idRefeicao = Integer.parseInt(request.getParameter("idRefeicao"));

		try
		{
			refeicao.atualizarRefeicao(idRefeicao, op_veg, descricao, turno);
			request.setAttribute("mensagem", "Refeicao atualizada com sucesso");

		} catch (ClassNotFoundException | SQLException | DescricaoNotFound | OpVegNotFound | TurnoNotFound e)
		{
			request.setAttribute("erro", "Algum erro ocorreu e a refeicao não pôde ser salva");
			e.printStackTrace();
		}
		request.getRequestDispatcher("ListarRefeicao").forward(request, response);

	}
}