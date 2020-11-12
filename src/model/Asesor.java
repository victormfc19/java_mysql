package model;

public class Asesor {

	private String cedula,nombre,telefono, fecha_nacimiento,genero, cliente, sede;
	private int edad;
	
	public Asesor(String cedula,String nombre,int edad,String telefono,String fecha_nacimiento,String genero, String cliente, String sede) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.genero = genero;
		this.cliente = cliente;
		this.sede = sede;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getId_cliente() {
		return cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.cliente = id_cliente;
	}

	public String getId_sede() {
		return sede;
	}

	public void setId_sede(String id_sede) {
		this.sede = id_sede;
	}

	public String imprimirAsesores() {
		return "Cedula: " + cedula + "\nNombre: "+nombre +"\nTelefono: "+telefono+ "\nEdad: "+edad+ "\nFecha nacimiento: "+fecha_nacimiento+""
				+ "\nGenero: "+genero+"\nSede: " + sede + "\nCliente: "+cliente + "\n\n";
	}

	
	
	
	
	
}
