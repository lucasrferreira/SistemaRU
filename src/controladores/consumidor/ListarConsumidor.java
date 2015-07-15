package controladores.consumidor;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Consumidor;
import entidades.value_objects.CPF;

@WebServlet("/ListarConsumidor")
public class ListarConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarConsumidores(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
		case "Criar":
			request.getRequestDispatcher("CriarConsumidor").forward(request,response);
			break;
		case "Atualizar":
			request.getRequestDispatcher("AtualizarConsumidor").forward(request,response);
			break;

		case "":
		default:
			listarConsumidores(request,response);			
		}
	}

	private void listarConsumidores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("consumidores", GerirCurso.listarCursos(request.getSession()));
		request.getRequestDispatcher("WEB-INF/consumidor/ListarConsumidores.jsp").forward(request,response);
	}


}
