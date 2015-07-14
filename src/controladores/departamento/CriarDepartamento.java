package controladores.departamento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/CriarDepartamento")
public class CriarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DepartamentoVO departamento;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarDepartamento(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarDepartamento").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/departamento/CriarDepartamento.jsp").forward(request,response);	
		}
	}

	private void criarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		departamento = formToVo(request);
		
		try {
			GerirDepartamento.criarDepartamento(departamento);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarDepartamento").forward(request,response);
		} catch (SiglaNotFoundException | NomeNotFoundException e2) {
			request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/departamento/CriarDepartamento.jsp").forward(request,response);
		}catch (SiglaAlreadyExistsException e) {
			request.setAttribute("erro", "Sigla informada já existe");
			request.getRequestDispatcher("WEB-INF/departamento/CriarDepartamento.jsp").forward(request,response);
		}		
		
	}
	
	
	private DepartamentoVO formToVo (HttpServletRequest request)
	{
		DepartamentoVO dpto = new DepartamentoVO();
		
		dpto.setNome((String) request.getParameter("nome"));
		dpto.setSigla((String) request.getParameter("sigla"));
		
		return dpto;
	}
	
	private void voToForm (HttpServletRequest request, DepartamentoVO dpto)
	{
		
		
	}

}