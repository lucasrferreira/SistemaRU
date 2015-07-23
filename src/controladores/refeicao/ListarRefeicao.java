package controladores.refeicao;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Refeicao;

@WebServlet("/ListarRefeicao")
public class ListarRefeicao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Refeicao refeicao = new Refeicao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarRefeicao(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarRefeicao").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarRefeicao").forward(request,response);
				break;

			case "":
			default:
				listarRefeicao(request,response);				
		}
	}

	private void listarRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try
			{
				request.setAttribute("refeicoes", refeicao.listarRefeicoes());
				request.getRequestDispatcher("WEB-INF/refeicao/ListarRefeicao.jsp").forward(request,response);
				
			} catch (Exception e)
			{
				request.setAttribute("refeicoes", new ArrayList<Refeicao>());
				request.getRequestDispatcher("WEB-INF/refeicao/ListarRefeicao.jsp").forward(request,response);
				e.printStackTrace();
			}
//			request.getRequestDispatcher("WEB-INF/refeicao/ListarRefeicao.jsp").forward(request,response);
		
		
	}
	
	
	

}
