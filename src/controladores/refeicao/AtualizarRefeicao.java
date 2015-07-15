package controladores.refeicao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirRefeicao;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.Refeicao;

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
				buscar(request, response);
		}
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idRefeicao = Integer.parseInt(request.getParameter("idRefeicao"));
		try {
			Refeicao refeicaoAntiga = GerirRefeicao.buscarRefeicao(idRefeicao);
			request.setAttribute("refeicao antiga",refeicaoAntiga);
			request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request,response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e2) {
			request.setAttribute("erro", "A refeicao informada nao existe");
			request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request,response);
		}				

	}
	
	private void atualizarRefeicaoAntiga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = (String) request.getParameter("descricao");
		String op_veg = (String) request.getParameter("op_veg");
		String turno  = (String) request.getParameter("descricao");
		int idRefeicao = Integer.parseInt(request.getParameter("idRefeicao"));
		
		
		if (descricao == "" || op_veg == ""){
			request.setAttribute("erro", "Uma refeicao deve conter uma descricao, turno e uma opcao vegetariana");
			request.getRequestDispatcher("WEB-INF/departamento/AtualizarDepartamento.jsp").forward(request,response);
		}else{
			try {
				GerirRefeicao.atualizarRefeicao(idRefeicao, op_veg, descricao, turno);
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				
			} catch (ClassNotFoundException | SQLException e2) {
				request.setAttribute("erro", "A refeicao informada nao existe");
				request.getRequestDispatcher("WEB-INF/refeicao/AtualizarRefeicao.jsp").forward(request,response);
			}			
		}
	}
}