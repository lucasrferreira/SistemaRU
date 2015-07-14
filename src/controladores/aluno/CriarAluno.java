package controladores.aluno;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirAluno;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.value_objects.AlunoVO;

@WebServlet("/CriarAluno")
public class CriarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AlunoVO aluno;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarAluno(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarAluno").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/aluno/CriarAluno.jsp").forward(request,response);	
		}
	}

	private void criarAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aluno = formToVo(request);
		
			GerirAluno.criarDepartamento(aluno);
			request.setAttribute("message", "Novo Aluno criado!");
			request.getRequestDispatcher("ListarAluno").forward(request,response);
	
		
	}
	
	
	private AlunoVO formToVo (HttpServletRequest request)
	{
		AlunoVO aluno = new AlunoVO();
		
		aluno.setNome((String) request.getParameter("nome"));
		
		
		return aluno;
	}
	
	private void voToForm (HttpServletRequest request, AlunoVO aluno)
	{
		
		
	}

}