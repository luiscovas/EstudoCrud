package crud.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.acao.AlteraEmpresa;
import crud.acao.ListaEmpresa;
import crud.acao.MostraEmpresa;
import crud.acao.NovaEmpresa;
import crud.acao.RemoveEmpresa;
import crud.contrato.Acao;
import crud.modelo.Empresa;

/**
 * Servlet implementation class CrudServlet
 */
@WebServlet("/crud")
public class CrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parametro = request.getParameter("p");
		String nomeDaClasse = "crud.acao." + parametro;		
		
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse);
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
		}
		
		String[] tipoEndereco = nome.split(":");
		if (tipoEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(request, response);	
		} else {
			response.sendRedirect(tipoEndereco[1]);
		}		
		
	}

}
