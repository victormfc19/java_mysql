package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Asesor;

public class AsesorJDBC {
	private static final String sql_select = "select a.cedula,a.nombre,a.telefono,a.edad,a.fecha_nacimiento,a.genero,s.nombre_sede, c.nombre_cliente \r\n" + 
			"from tbl_asesor as a JOIN tbl_sede as s ON a.id_sede = s.id JOIN tbl_cliente as c ON a.id_cliente = c.id";
	private static final String sql_insert = "insert into tbl_asesor (cedula,nombre,telefono,edad,fecha_nacimiento,genero,id_cliente,id_sede) "
			                                  + "values (?,?,?,?,?,?,?,?)";
	
	private static final String sql_update = "update tbl_asesor set cedula=?, nombre=?, telefono=?, edad=?, fecha_nacimiento=?, genero=?, id_cliente=?, id_sede=?"
			                               + "where cedula=?";
	
	private static final String sql_delete =  "delete from tbl_asesor where cedula=?";
	
	public AsesorJDBC() {
		
	}
	
	public void insert(String cedula,String nombre,String telefono,int edad,String fecha_nacimiento,String genero, String idCliente, String idSede) {
		Connection cnx = null;
		PreparedStatement ps = null;
		try {
			cnx = Conexion.getConexion();
			
			ps = cnx.prepareStatement(sql_insert);
			ps.setString(1, cedula);
			ps.setString(2, nombre);
			ps.setString(3, telefono);
			ps.setInt(4, edad);
			ps.setString(5, fecha_nacimiento);
			ps.setString(6, genero);
			ps.setString(7, idCliente);
			ps.setString(8, idSede);
			int respuesta = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			Conexion.close(cnx);
			Conexion.close(ps);
		}
	}
	
	public List<Asesor> select(){
		Connection cnx = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Asesor> asesores = new ArrayList<>();
		try {
			
			cnx = Conexion.getConexion();
			ps = cnx.prepareStatement(sql_select);
			rs = ps.executeQuery();
			
			String cedula, nombre,telefono, fecha_nacimiento, genero, sede, cliente;
			int edad;
			
			while(rs.next()) {
				
				cedula = rs.getString("cedula");
				nombre = rs.getString("nombre");
				edad = rs.getInt("edad");
				telefono = rs.getString("telefono");
				fecha_nacimiento = rs.getString("fecha_nacimiento");
				genero = rs.getString("genero");
				sede = rs.getString("nombre_sede");
				cliente = rs.getString("nombre_cliente");
				
				asesores.add(new Asesor(cedula,nombre,edad,telefono,fecha_nacimiento,genero,cliente,sede));
				
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			Conexion.close(cnx);
			Conexion.close(ps);
			Conexion.close(rs);
		}
		
		return asesores;
	}
	
	public void delete(String cedula){
		Connection cnx = null;
		PreparedStatement ps = null;
		try {
			
			cnx = Conexion.getConexion();
			ps = cnx.prepareStatement(sql_delete);
			ps.setString(1, cedula);
			int respuesta = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			Conexion.close(cnx);
			Conexion.close(ps);
		}
		
	}
	
	public void update(String cedula,String nombre,String telefono,int edad,String fecha_nacimiento,String genero, String idCliente, String idSede,String documento) {
		Connection cnx = null;
		PreparedStatement ps = null;
		try {
			cnx = Conexion.getConexion();
			
			ps = cnx.prepareStatement(sql_update);
			ps.setString(1, cedula);
			ps.setString(2, nombre);
			ps.setString(3, telefono);
			ps.setInt(4, edad);
			ps.setString(5, fecha_nacimiento);
			ps.setString(6, genero);
			ps.setString(7, idCliente);
			ps.setString(8, idSede);
			ps.setString(9, documento);
			int respuesta = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			Conexion.close(cnx);
			Conexion.close(ps);
		}
	}
	
	
}
