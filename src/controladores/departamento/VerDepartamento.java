package controladores.departamento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/VerDepartamento")
public class VerDepartamento extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String acao = (String) request.getParameter("acaoVer");
		if (acao == null)
			acao = "";

		switch (acao)
		{
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request, response);
				break;
			default:
				buscar(request, response);
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response)
	{
		DepartamentoVO departamentoAntigo = formToVo(request);
		try
		{
			departamentoAntigo = GerirDepartamento.buscarDepartamento(departamentoAntigo);
			request.setAttribute("departamento antigo", departamentoAntigo);
			try
			{
				request.getRequestDispatcher("WEB-INF/departamento/VerDepartamento.jsp").forward(request, response);
			} catch (ServletException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DepartamentoNotFound e)
		{
			request.setAttribute("erro", "Departamento não existe!");
			try
			{
				request.getRequestDispatcher("WEB-INF/VerDepartamento.jsp").forward(request, response);
			} catch (ServletException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private DepartamentoVO formToVo(HttpServletRequest request)
	{
		DepartamentoVO dpto = new DepartamentoVO();

		dpto.setNome((String) request.getParameter("nome"));
		dpto.setSigla((String) request.getParameter("sigla"));

		return dpto;
	}

	private void voToForm(HttpServletRequest request, DepartamentoVO dpto)
	{

	}
}