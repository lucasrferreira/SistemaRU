package controladores.aluno;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirAluno;
import controladores.ccu.exceptions.CursoNotFound;
import entidades.value_objects.AlunoVO;

@WebServlet("/AtualizarDepartamento")
public class AtualizarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarAluno").forward(request,response);
				break;
			case "Atualizar":
				atualizarAlunoAntigo(request,response);
				break;
			default:
				try {
					AlunoVO departamentoAntigo = GerirAluno.buscarDepartamento(request.getSession(),request.getParameter("cpf"));
					request.setAttribute("aluno antigo",departamentoAntigo);
					request.getRequestDispatcher("WEB-INF/aluno/AtualizarAluno.jsp").forward(request,response);
				} catch (CursoNotFound e2) {
					request.setAttribute("erro", "O aluno informado nao existe");
					request.getRequestDispatcher("WEB-INF/departamento/AtualizarAluno.jsp").forward(request,response);
				}				
		}
	}
	
	
	private void atualizarAlunoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String curso = (String) request.getParameter("curso");
		
		if (nome == "" || curso == ""){
			request.setAttribute("erro", "Um aluno deve conter um nome e uma curso");
			request.getRequestDispatcher("WEB-INF/aluno/AtualizarAluno.jsp").forward(request,response);
		}else{
			try {
				GerirAluno.atualizarDepartamento(request.getSession(), nome, curso);
				request.getRequestDispatcher("ListarAluno").forward(request,response);
				
			} catch (CursoNotFound e2) {
				request.setAttribute("erro", "O aluno informado nao existe");
				request.getRequestDispatcher("WEB-INF/aluno/AtualizarAluno.jsp").forward(request,response);
			}			
		}
	}
}