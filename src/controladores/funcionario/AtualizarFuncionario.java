package controladores.funcionario;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirFuncionario;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;

@WebServlet("/AtualizarFuncionario")
public class AtualizarFuncionario extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao)
		{
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarFuncionario").forward(request, response);
				break;
			case "Atualizar":
				atualizarFuncionarioAntigo(request, response);
				break;
			default:

		}
	}

	private void atualizarFuncionarioAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		int matricula = (Integer.parseInt(request.getParameter("matricula")));
		int ano = (Integer.parseInt(request.getParameter("anoIngresso")));
		String sexo = (String) request.getParameter("sexo");
		String cpf = (String) request.getParameter("cpf");
		String titulo = (String) request.getParameter("titulo");
		String departamento = (String) request.getParameter("departamento");

		
		
		
		try
		{
			GerirFuncionario.atualizarFuncionario(nome, cpf, sexo, matricula, titulo, ano, departamento);
		} catch (ClassNotFoundException | SiglaNotFoundException | NomeNotFoundException | SiglaAlreadyExistsException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("ListarFuncionario").forward(request,response);
				
					
		
	}
}