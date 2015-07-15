package controladores.consumidor;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.value_objects.TurnoVO;


@WebServlet("/CriarDepartamento")
public class CriarConsumidor extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	ConsumidorVO consumidor;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoCriar");

		if (acao != null)
		{
			switch (acao)
			{
				case "Criar":
					criarConsumidor(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarConsumidor").forward(request, response);
			}
		} else
		{
			request.getRequestDispatcher("WEB-INF/consumidor/CriarConsumidor.jsp").forward(request, response);
		}
	}

	private void criarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String nome = (String) request.getParameter("nome");
		int matricula = (Integer.parseInt(request.getParameter("matricula")));
		int anoIngresso = (Integer.parseInt(request.getParameter("anoIngresso")));
		
		if(Sexo.MASCULINO.getSexo().equals((String)request.getParameter("sexo")))
			String sex = Sexo.setSexo(Sexo.MASCULINO);
		if(Sexo.FEMININO.getSexo().equals((String)request.getParameter("sexo")))
			String sex = Sexo.setSexo(Sexo.FEMININO);

		if(Titulo.ESPECIALIZACAO.getTitulo().equals((String)request.getParameter("titulo")))
			String titulo = Titulo.setTitulo(Titulo.ESPECIALIZACAO);
		if(Titulo.MESTRADO.getTitulo().equals((String)request.getParameter("titulo")))
			String titulo = Titulo.setTitulo(Titulo.MESTRADO);
		if(Titulo.DOUTORADO.getTitulo().equals((String)request.getParameter("titulo")))
			String titulo = Titulo.setTitulo(Titulo.DOUTORADO);
		
		

		try
		{

			GerirDepartamento.criarDepartamento(sigla, nome);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarDepartamento").forward(request, response);
		} catch (ClassNotFoundException | SQLException e)
		{
			request.setAttribute("erro", "O sistema encontrou problemas para se comunicar com o banco. Sorry!");
			request.getRequestDispatcher("WEB-INF/departamento/CriarDepartamento.jsp").forward(request, response);
			e.printStackTrace();

		} catch (SiglaNotFoundException | NomeNotFoundException e2)
		{
			request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/departamento/CriarDepartamento.jsp").forward(request, response);
		} catch (SiglaAlreadyExistsException e)
		{
			request.setAttribute("erro", "Sigla informada ja existe");
			request.getRequestDispatcher("WEB-INF/departamento/CriarDepartamento.jsp").forward(request, response);
		}

	}



}