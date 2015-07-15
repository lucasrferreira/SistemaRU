package controladores.aluno;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirAluno;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;

@WebServlet("/CriarAluno")
public class CriarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarAluno(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarConsumidor").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/aluno/CriarAluno.jsp").forward(request,response);	
		}
	}

	private void criarAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		int matricula = (Integer.parseInt(request.getParameter("matricula")));
		int ano = (Integer.parseInt(request.getParameter("anoIngresso")));
		String sexo = (String) request.getParameter("sexo");
		String cpf = (String) request.getParameter("cpf");
		String titulo = (String) request.getParameter("titulo");
		String curso = (String) request.getParameter("curso");
		
		try
		{
			GerirAluno.criarAluno(nome, cpf, sexo, matricula, titulo, ano, curso);
			request.setAttribute("message", "Novo Aluno criado!");
			request.getRequestDispatcher("ListarAluno").forward(request,response);
		
		} catch (ClassNotFoundException | SiglaNotFoundException | NomeNotFoundException | SiglaAlreadyExistsException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	
		
	}
	


}