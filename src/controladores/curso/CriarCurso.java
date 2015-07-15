package controladores.curso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirCurso;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Curso;

@WebServlet("/CriarCurso")
public class CriarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		Collection<Curso> cursosDisponiveis = GerirCurso.listarCursos();
		request.setAttribute("departamentosDisponiveis", cursosDisponiveis);
		
		if (acao != null){
			switch (acao) {
				case "Criar":
				try {
					criarCurso(request,response);
				} catch (DepartamentoNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				default:
					request.getRequestDispatcher("ListarCurso").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/curso/CriarCurso.jsp").forward(request,response);	
		}
	}

	private void criarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DepartamentoNotFound {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		try {
			try
			{
				GerirCurso.criarCurso(nome, sigla, request.getParameter("aluno"));
				request.setAttribute("message", "Novo departamento criado!");
				request.getRequestDispatcher("ListarCurso").forward(request,response);
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		} catch (SiglaNotFoundException | NomeNotFoundException e2) {
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
			request.getRequestDispatcher("WEB-INF/curso/CriarCurso.jsp").forward(request,response);
			
		}catch (SiglaAlreadyExistsException e) {
			request.setAttribute("erro", "Sigla informada ja existe");
			request.getRequestDispatcher("WEB-INF/curso/CriarCurso.jsp").forward(request,response);

		}
	}
}