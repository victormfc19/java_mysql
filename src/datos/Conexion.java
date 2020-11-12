package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	
	private static final String url = "jdbc:mysql://localhost:3306/practica_java_mysql?useSSL=false&serverTimezone=UTC";
		
	public static Connection getConexion() throws SQLException {
		 return DriverManager.getConnection(url,"root","");
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void close(Connection cnx) {
		try {
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}
}
