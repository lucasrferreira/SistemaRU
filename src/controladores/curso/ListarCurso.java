package controladores.curso;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.NenhumResultado;
import entidades.Curso;

@WebServlet("/ListarCurso")
public class ListarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Curso curso = new Curso();
	
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
			case "":
			default:
				listarCursos(request,response);				
		}
	}

	
	private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			request.setAttribute("cursos", curso.listarCursos());
			request.getRequestDispatcher("WEB-INF/curso/ListarCurso.jsp").forward(request,response);
		} catch (ClassNotFoundException | NenhumResultado | BancoErro | SQLException e)
		{		
			request.getRequestDispatcher("WEB-INF/curso/ListarCurso.jsp").forward(request,response);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
