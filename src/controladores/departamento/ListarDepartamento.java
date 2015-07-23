package controladores.departamento;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.NenhumResultado;
import entidades.Departamento;

@WebServlet("/ListarDepartamento")
public class ListarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Departamento departamento = new Departamento();
	
	
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
			case "":
			default:
				listarDepartamentos(request,response);				
		}
	}

	private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			request.setAttribute("departamentos", departamento.listarDepartamentos());
			request.getRequestDispatcher("WEB-INF/departamento/ListarDepartamento.jsp").forward(request,response);

		} catch (ClassNotFoundException | BancoErro | NenhumResultado | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
