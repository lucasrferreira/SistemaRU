package controladores.refeicao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirRefeicao;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.value_objects.RefeicaoVO;

@WebServlet("/AtualizarRefeicao")
public class AtualizarRefeicao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarRefeicao").forward(request,response);
				break;
			case "Atualizar":
				atualizarRefeicaoAntiga(request,response);
				break;
			default:
				try {
					RefeicaoVO departamentoAntigo = GerirRefeicao.buscarDepartamento(request.getSession(),request.getParameter("sigla"));
					request.setAttribute("refeicao antiga",departamentoAntigo);
					request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request,response);
				} catch (DepartamentoNotFound e2) {
					request.setAttribute("erro", "A refeicao informada nao existe");
					request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request,response);
				}				
		}
	}
	
	
	private void atualizarRefeicaoAntiga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = (String) request.getParameter("descricao");
		String op_veg = (String) request.getParameter("op_veg");
		
		if (descricao =="" || op_veg ==""){
			request.setAttribute("erro", "Uma refeicao deve conter uma descricao, turno e uma opcao vegetariana");
			request.getRequestDispatcher("WEB-INF/departamento/AtualizarDepartamento.jsp").forward(request,response);
		}else{
			try {
				GerirRefeicao.atualizarDepartamento(request.getSession(), descricao, op_veg);
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				
			} catch (DepartamentoNotFound e2) {
				request.setAttribute("erro", "A refeicao informada nao existe");
				request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request,response);
			}			
		}
	}
}