package controladores.funcionario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidades.value_objects.FuncionarioVO;

@WebServlet("/AtualizarDepartamento")
public class AtualizarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarFuncionario").forward(request,response);
				break;
			case "Atualizar":
				atualizarFuncionarioAntigo(request,response);
				break;
			default:
				try {
					FuncionarioVO funcionarioAntigo = GerirFuncionario.buscarDepartamento(request.getSession(),request.getParameter("sigla"));
					request.setAttribute("funcionario antigo", funcionarioAntigo);
					request.getRequestDispatcher("WEB-INF/funcionario/AtualizarFuncionario.jsp").forward(request,response);
				} catch (DepartamentoNotFound e2) {
					request.setAttribute("erro", "O funcionario informado nao existe");
					request.getRequestDispatcher("WEB-INF/departamento/AtualizarFuncionario.jsp").forward(request,response);
				}				
		}
	}
	
	
	private void atualizarFuncionarioAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		if (nome=="" || sigla==""){
			request.setAttribute("erro", "Um funcionario deve conter ");
			request.getRequestDispatcher("WEB-INF/funcionario/AtualizarFuncionario.jsp").forward(request,response);
		}else{
		
				GerirFuncionario.atualizarFuncionario(request.getSession(), , );
				request.getRequestDispatcher("ListarFuncionario").forward(request,response);
				
					
		}
	}
}