package crud.acao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import crud.contrato.Acao;
import crud.modelo.ConnectionFactory;
import crud.modelo.Empresa;

public class AlteraEmpresa implements Acao {
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String nomeEmpresa = request.getParameter("nome");
		String dataEmpresa = request.getParameter("data");
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
	
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");   
		    Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmpresa);  
		    String dataAbertura = formatter.format(dataFormatada);				
			
			Connection connection = connectionFactory.Conectar();
			PreparedStatement stm = connection.prepareStatement("UPDATE EMPRESA SET NOME = ?, DT_ABERTURA = ?  WHERE ID = ?");

			stm.setString(1, nomeEmpresa);
			stm.setString(2, dataAbertura);			
			stm.setInt(3, id);
			stm.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return "redirect:crud?p=ListaEmpresa";
	}

}
