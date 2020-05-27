package crud.acao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.contrato.Acao;
import crud.modelo.ConnectionFactory;
import crud.modelo.Empresa;

public class MostraEmpresa implements Acao {
	
    public String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   	
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		Empresa empresa = new Empresa();		
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			Connection connection = connectionFactory.Conectar();
			PreparedStatement stm = connection.prepareStatement("SELECT * FROM EMPRESA WHERE ID = ?");
			stm.setInt(1, id);
			stm.execute();
			
			ResultSet rst = stm.getResultSet();
			while (rst.next()) {
				empresa.setId(rst.getInt("ID"));
				empresa.setNome(rst.getString("NOME"));
				empresa.setDataAbertura(rst.getDate("DT_ABERTURA"));				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}		
		
		request.setAttribute("empresa", empresa);
		return "forward:formAlteraEmpresa.jsp";
		
	}

}
