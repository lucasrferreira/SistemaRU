package controladores.funcionario;

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
import entidades.Funcionario;

@WebServlet("/CriarFuncionario")
public class CriarFuncionario extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	Funcionario					funcionario			= new Funcionario();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoCriar");

		if (acao != null)
		{
			switch (acao)
			{
				case "Criar":
					criarFuncionario(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarFuncionario").forward(request, response);
			}
		} else
		{
			abrirForm(request, response);
		}
	}

	private void abrirForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Departamento departamento = new Departamento();
			request.setAttribute("DepartamentosDisponiveis", departamento.listarDepartamentos());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("WEB-INF/funcionario/CriarFuncionario.jsp").forward(request, response);
	}

	private void criarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nome = (String) request.getParameter("nome");
		int matricula = (Integer.parseInt(request.getParameter("matricula")));
		int ano = (Integer.parseInt(request.getParameter("anoIngresso")));
		String sexo = (String) request.getParameter("sexo");
		String cpf = (String) request.getParameter("cpf");
		String titulo = (String) request.getParameter("titulo");
		String departamento = (String) request.getParameter("departamento");

		try
		{
			funcionario.criarFuncionario(nome, cpf, sexo, matricula, titulo, ano, departamento);
			request.setAttribute("message", "Novo Funcionario criado!");
			request.getRequestDispatcher("ListarFuncionario").forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}