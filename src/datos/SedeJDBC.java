package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Sede;

public class SedeJDBC {
	
	private static final String sql_select = "select * from tbl_sede";
	private static final String sql_insert = "insert into tbl_sede (id,nombre_sede) values (?,?)";
	private static final String sql_update = "update tbl_sede set id = ?, nombre_sede = ? where id = ?";
	private static final String sql_delete =  "delete from tbl_sede where id = ?";

	public SedeJDBC() {
		
	}
	
	public void insert(String id, String nombre) {
		Connection cnx = null;
		PreparedStatement ps = null;
		try {
			cnx = Conexion.getConexion();
			
			ps = cnx.prepareStatement(sql_insert);
			ps.setString(1, id);
			ps.setString(2, nombre);
			int respuesta = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			Conexion.close(cnx);
			Conexion.close(ps);
		}
	}
	
	public List<Sede> select(){
			
			Connection cnx = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			List<Sede> sedes = new ArrayList<>();
			try {
				cnx = Conexion.getConexion();
				ps = cnx.prepareStatement(sql_select);
				rs = ps.executeQuery();
				
				String id, nombre;	
				while(rs.next()) {
					id = rs.getString("id");
					nombre = rs.getString("nombre_sede");
					sedes.add(new Sede(id,nombre));
				}
				
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}finally {
				Conexion.close(cnx);
				Conexion.close(ps);
				Conexion.close(rs);
			}
			
			return sedes;
		}
	
	public void update(String id_nuevo,String nombre_nuevo, String id) {
		Connection cnx = null;
		PreparedStatement ps = null;
		try {
			cnx = Conexion.getConexion();
			
			ps = cnx.prepareStatement(sql_update);
			ps.setString(1, id_nuevo);
			ps.setString(2, nombre_nuevo);
			ps.setString(2, id);
			int respuesta = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			Conexion.close(cnx);
			Conexion.close(ps);
		}
	}
	
	public void delete(String id) {
		Connection cnx = null;
		PreparedStatement ps = null;
		try {
			cnx = Conexion.getConexion();
			
			ps = cnx.prepareStatement(sql_delete);
			ps.setString(1, id);

			int respuesta = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			Conexion.close(cnx);
			Conexion.close(ps);
		}
	}
	
	
}
