package crud.acao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.contrato.Acao;
import crud.modelo.ConnectionFactory;
import crud.modelo.Empresa;

public class NovaEmpresa implements Acao {
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String data = request.getParameter("data");
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		try {
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");   
		    Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(data);  
		    String dataAbertura = formatter.format(dataFormatada);	
		    
			Connection connection = connectionFactory.Conectar();
			PreparedStatement stm = connection.prepareStatement("INSERT INTO EMPRESA (NOME, DT_ABERTURA) VALUES (?, ?)");
			stm.setString(1, nome);
			stm.setString(2, dataAbertura);
			stm.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);			
		}

		return "redirect:crud?p=ListaEmpresa";		
	}	

}
