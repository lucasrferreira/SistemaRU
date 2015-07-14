package controladores.aluno;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.value_objects.AlunoVO;

@WebServlet("/ListarAluno")
public class ListarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarAluno(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarAluno").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarAluno").forward(request,response);
				break;
			case "":
			default:
				listarAluno(request,response);				
		}
	}

	private void listarAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("refeicao", GerirAluno.listarDepartamentos());
		request.getRequestDispatcher("WEB-INF/aluno/ListarAluno.jsp").forward(request,response);
	}
	
	
	private AlunoVO formToVo (HttpServletRequest request)
	{
		
		
		return new AlunoVO();
	}
	
	private void voToForm (HttpServletRequest request, AlunoVO aluno)
	{
		
		
	}

}
