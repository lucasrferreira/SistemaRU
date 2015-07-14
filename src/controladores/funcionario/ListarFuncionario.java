package controladores.funcionario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.value_objects.FuncionarioVO;

@WebServlet("/ListarFuncionario")
public class ListarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarFuncionario(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarFuncionario").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarFuncionario").forward(request,response);
				break;

			case "":
			default:
				listarFuncionario(request,response);				
		}
	}

	private void listarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("funcionario", GerirFuncionario.listarFuncionario());
		request.getRequestDispatcher("WEB-INF/funcionario/ListarFuncionario.jsp").forward(request,response);
	}
	
	
	private FuncionarioVO formToVo (HttpServletRequest request)
	{
		
		
		return new FuncionarioVO();
	}
	
	private void voToForm (HttpServletRequest request, FuncionarioVO dpto)
	{
		
		
	}

}
