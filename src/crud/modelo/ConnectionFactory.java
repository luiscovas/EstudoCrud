package crud.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection Conectar() throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/estudo_crud?useTimezone=true&serverTimezone=UTC", "root", "root");
			} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
			}
	}	

}
