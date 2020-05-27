package crud.acao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crud.contrato.Acao;
import crud.modelo.ConnectionFactory;
import crud.modelo.Empresa;

public class ListaEmpresa implements Acao {
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> lista = new ArrayList<Empresa>();
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			Connection connection = connectionFactory.Conectar();
			PreparedStatement stm = connection.prepareStatement("SELECT * FROM EMPRESA");
			stm.execute();
			
			ResultSet rst = stm.getResultSet();
			while (rst.next()) {
				Empresa empresa = new Empresa();
				empresa.setId(rst.getInt("ID"));
				empresa.setNome(rst.getString("NOME"));
				empresa.setDataAbertura(rst.getDate("DT_ABERTURA"));
				lista.add(empresa);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		request.setAttribute("empresas", lista);
		return "forward:listaEmpresa.jsp";		

	}

}

