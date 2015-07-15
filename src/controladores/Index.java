package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = (String) request.getParameter("navegacao");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Departamento":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			case "Curso":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			case "Consumidor":
				request.getRequestDispatcher("ListarConsumidor").forward(request,response);
				break;
			case "":
			default:
				request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request,response);
		}
	}

}
