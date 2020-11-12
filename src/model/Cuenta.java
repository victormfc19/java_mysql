package model;

public class Cuenta {
	
	private String numero, fecha_creacion, fecha_modificacion, id_cliente;
	private int estado;
	private long saldo;
	
	public Cuenta(String numero, long saldo, String fecha_creacion, String fecha_modificacion, int estado, String id_cliente) {
		this.numero = numero;
		this.fecha_creacion = fecha_creacion;
		this.fecha_modificacion = fecha_modificacion;
		this.id_cliente = id_cliente;
		this.saldo = saldo;
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}


	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [numero=" + numero + ", fecha_creacion=" + fecha_creacion + ", fecha_modificacion="
				+ fecha_modificacion + ", id_cliente=" + id_cliente + ", saldo=" + saldo + ", estado=" + estado + "]";
	}
	
	
	
	
}
