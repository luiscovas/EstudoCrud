package crud.acao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.contrato.Acao;
import crud.modelo.ConnectionFactory;

public class RemoveEmpresa implements Acao {
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			Connection connection = connectionFactory.Conectar();		
			PreparedStatement stm = connection.prepareStatement("DELETE FROM EMPRESA WHERE ID = ?");
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return "redirect:crud?p=ListaEmpresa";		
	}	

}
