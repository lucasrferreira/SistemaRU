package controladores.departamento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/ListarDepartamento")
public class ListarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarDepartamentos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarDepartamento").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarDepartamento").forward(request,response);
				break;

			case "":
			default:
				listarDepartamentos(request,response);				
		}
	}

	private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("departamentos", GerirDepartamento.listarDepartamentos());
		request.getRequestDispatcher("WEB-INF/departamento/ListarDepartamento.jsp").forward(request,response);
	}
	
	
	private DepartamentoVO formToVo (HttpServletRequest request)
	{
		
		
		return new DepartamentoVO();
	}
	
	private void voToForm (HttpServletRequest request, DepartamentoVO dpto)
	{
		
		
	}

}
