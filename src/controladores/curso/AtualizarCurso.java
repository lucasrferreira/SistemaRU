package controladores.curso;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirCurso;
import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.Departamento;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/AtualizarCurso")
public class AtualizarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		Collection<DepartamentoVO> departamentosDisponiveis = Departamento
				._listarDepartamentosDisponiveis();
		request.setAttribute("departamentosDisponiveis",
				departamentosDisponiveis);

		if (acao == null)
			acao = "";

		switch (acao) {
		case "Cancelar":
		case "Voltar":
			request.getRequestDispatcher("ListarCurso").forward(request,
					response);
			break;
		case "Atualizar":
			try {
				atualizarCursoAntigo(request, response);
			} catch (CursoNotFound e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DepartamentoNotFound e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			CursoVO cursoAntigo;
			try {
				cursoAntigo = buscarCursoAntigo(request);
				request.setAttribute("curso antigo", cursoAntigo);
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(
						request, response);
			} catch (CursoNotFound e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "erro");
				e.printStackTrace();
			}

		}
	}

	private CursoVO buscarCursoAntigo(HttpServletRequest request)
			throws ServletException, IOException, CursoNotFound {
		
		CursoVO cursoAntigo = new CursoVO("", request.getParameter("sigla"),null);
		try {
			cursoAntigo = GerirCurso.buscarCurso(request.getSession(),
					request.getParameter("sigla"));
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Curso não existe!");
			cursoAntigo = null;
		}
		if (cursoAntigo == null) {
			request.setAttribute("erro", "Curso não existe!");
			cursoAntigo = null;
		}

		return cursoAntigo;
	}

	private void atualizarCursoAntigo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, CursoNotFound, DepartamentoNotFound {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");

		DepartamentoVO departamentoSelecionado = GerirDepartamento.buscarDepartamento(request.getSession(),request.getParameter("departamento"));
		CursoVO curso = new CursoVO(nome, sigla, departamentoSelecionado);

		if (curso.getNome() == "" || curso.getSigla() == ""
				|| curso.getDepartamento() == null) {
			request.setAttribute("erro",
					"Um curso deve conter um nome, uma sigla e um departamento");
			request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(
					request, response);
		} else {
			CursoVO cursoAntigo = buscarCursoAntigo(request);
			if (cursoAntigo == null) {
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp")
						.forward(request, response);
			} else {
				GerirCurso.atualizarCurso(request.getSession(), curso.getNome(), curso.getSigla(), curso.getDepartamento().getSigla());
				request.getRequestDispatcher("ListarCurso").forward(request, response);
			}
		}
	}

}