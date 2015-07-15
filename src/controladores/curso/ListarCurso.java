package controladores.curso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirCurso;

@WebServlet("/ListarCurso")
public class ListarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarCursos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarCurso").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarCurso").forward(request,response);
				break;

			case "":
			default:
				listarCursos(request,response);				
		}
	}

	
	private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("cursos", GerirCurso.listarCursos());
		request.getRequestDispatcher("WEB-INF/curso/ListarCurso.jsp").forward(request,response);
	}

}
