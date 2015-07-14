package controladores.funcionario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.value_objects.AlunoVO;
import entidades.value_objects.FuncionarioVO;


@WebServlet("/CriarFuncionario")
public class CriarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FuncionarioVO funcionario;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarFuncionario(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarFuncionario").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/funcionario/CriarFuncionario.jsp").forward(request,response);	
		}
	}

	private void criarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		funcionario = formToVo(request);
		
			GerirFuncionario.criarDepartamento(funcionario);
			request.setAttribute("message", "Novo Funcionario criado!");
			request.getRequestDispatcher("ListarFuncionario").forward(request,response);
	
		
	}
	
	
	private FuncionarioVO formToVo (HttpServletRequest request)
	{
		FuncionarioVO aluno = new FuncionarioVO();
		
		funcionario.setNome((String) request.getParameter("nome"));
		
		
		return funcionario;
	}
	
	private void voToForm (HttpServletRequest request, FuncionarioVO funcionario)
	{
		
		
	}

}