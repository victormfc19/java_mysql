package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import datos.AsesorJDBC;
import datos.ClienteJDBC;
import datos.SedeJDBC;
import model.Asesor;
import model.Cliente;
import model.Cuenta;
import model.Sede;

public class Principal {
	
	/*
	 * Instancias de las clases del paquete datos, encargadas de las operaciones CRUD
	 * y la comunicacion con la base de datos.
	 */
	private static ClienteJDBC manejo_clientes = new ClienteJDBC();
	private static SedeJDBC manejo_sedes = new SedeJDBC();
	private static AsesorJDBC manejo_asesores = new AsesorJDBC();
	

	public static int menu() {
		return Integer.parseInt( JOptionPane.showInputDialog("1. Registrar un asesor\n2. Listar asesores\n3. Editar asesor\n4. Eliminar asesor"
				+ "\n5. Ingresar una nueva sede\n6. Ingresar un nuevo cliente\n7. Salir") );
	}
	
	// Metodo para validar que la cedula y el telefono contengan solo numeros.
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * Metodo para calcular la edad del asesor
	 * teniendo en cuenta su año, mes y dia de nacimiento
	 */
	public static int calcularEdad(String anio, String mes, String dia) {
		Calendar cal = Calendar.getInstance();
		int anioActual = cal.get(Calendar.YEAR);
		int mesActual = cal.get(Calendar.MONTH)+1;
		int diaActual = cal.get(Calendar.DATE);
		
		int edad = anioActual - Integer.parseInt(anio);
		
		if(Integer.parseInt(mes) > mesActual)
			edad = edad - 1;
		else if(Integer.parseInt(mes) < mesActual) {
			edad = anioActual - Integer.parseInt(anio);
		}else if(diaActual < Integer.parseInt(dia)) {
			edad = edad - 1;
		}
		
		return edad;
	}
	
	/*
	 * Metodo para ingresar asesores a la BBDD, si el parametro documento viene en null es porque es un insert. (Case 1 del Switch)
	 * Si por el contrario viene un documento es porque se pretende editar un asesor. (Case 3 del Switch)
	 */
	public static void registrar_datos(String documento) {
		Calendar cal = Calendar.getInstance();
		int anioActual = cal.get(Calendar.YEAR);
		String cedula, nombre,telefono, fechaNacimiento, genero, idSede, idCliente;
		int edad;
	
		cedula = JOptionPane.showInputDialog("Por favor ingrese la cedula");	
		while( !isNumeric(cedula) )	
			cedula = JOptionPane.showInputDialog("Por favor ingrese una cedula valida");
		
		nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre");
		telefono = JOptionPane.showInputDialog("Por favor ingrese el telefono");
		
		while( !isNumeric(telefono) )	
			telefono = JOptionPane.showInputDialog("Por favor ingrese un telefono valido");
		
		/** CODIGO PARA ESTABLECER LA FECHA NACIMIENTO EN FORMATO (AAAA-MM-DD) **/
		String anio,mes,dia;
		anio = JOptionPane.showInputDialog("Digite el año de nacimiento (YYYY)");
		while(Integer.parseInt(anio) < 1900 || Integer.parseInt(anio) > anioActual)
			anio = JOptionPane.showInputDialog("Digite un año de nacimiento VALIDO (YYYY)");
		
		mes =  JOptionPane.showInputDialog("Digite el mes de nacimiento (MM)");
		while(Integer.parseInt(mes) <= 0 || Integer.parseInt(mes) > 12)
			mes = JOptionPane.showInputDialog("Digite un mes de nacimiento VALIDO (1-12)");
		
		dia = JOptionPane.showInputDialog("Digite el dia de nacimiento  (DD)");
		while(Integer.parseInt(dia) <= 0 || Integer.parseInt(dia) > 31)
			dia = JOptionPane.showInputDialog("Digite un dia de nacimiento VALIDO (1-31)");
		
		fechaNacimiento = anio.concat("-").concat(mes).concat("-").concat(dia);
		
		edad = calcularEdad(anio, mes, dia);
		genero = JOptionPane.showInputDialog("Ingrese el genero (M o F)");
		
		String listarSedes = "", listarClientes = "";
		for(Sede s: manejo_sedes.select()) 
			listarSedes += s.imprimirSedes();
		
		idSede = JOptionPane.showInputDialog(listarSedes + "\nIngrese el numero de la sede");
		while( !isNumeric(idSede) || Integer.parseInt(idSede) > manejo_sedes.select().size() )	
			idSede = JOptionPane.showInputDialog(listarSedes + "\nIngrese un numero de sede valido");
		
		for(Cliente c: manejo_clientes.select())
			listarClientes += c.imprimirClientes();
		
		idCliente = JOptionPane.showInputDialog(listarClientes + "\nIngrese el numero del cliente");
		while(!isNumeric(idCliente) || Integer.parseInt(idCliente) > manejo_clientes.select().size())	
			idCliente = JOptionPane.showInputDialog(listarClientes + "\nIngrese un numero de cliente valido");
		
		
		if(documento == null) // Si es null utilizo el metodo para insertar un nuevo asesor
			manejo_asesores.insert(cedula, nombre, telefono, edad, fechaNacimiento, genero, idCliente, idSede);
		else				// Si no, es porque voy a modificar uno existente
			manejo_asesores.update(cedula, nombre, telefono, edad, fechaNacimiento, genero, idCliente, idSede, documento);
		
	}
	
	// Metodo para listar los asesores
	public static void listar_asesores() {
		List<Asesor> listado_asesores = manejo_asesores.select();
		System.out.println("** Listado asesores Konecta **");
		for(Asesor a: listado_asesores) 
			System.out.println(a.imprimirAsesores());
		
	}
	
	public static boolean validarDocumento(String cedula) {
		List<Asesor> listado_asesores = manejo_asesores.select();
		for(Asesor a: listado_asesores) 
			if( a.getCedula().equals( cedula ))
				return true;
		
		return false;	
	}
	
	public static void main(String[] args) {
		int opcion;
		String documento,id,nombre;
		
		do {
			 opcion = menu();
			 
			 switch( opcion ) {
			 
			 case 1:
				 	registrar_datos(null);
				 	break;
			 case 2:
				 	listar_asesores();
				 	break;
				 	
			 case 3:
				 	documento = JOptionPane.showInputDialog("Ingrese la cedula del asesor a modificar");
				 	registrar_datos(documento);
				 	break;
				 	
			 case 4:
				    documento = JOptionPane.showInputDialog("Ingrese la cedula del asesor a Eliminar");
				    if( !validarDocumento(documento) )
				    	System.out.println("No existe ningun asesor con ese numero de cedula!");
				    else
				    	 manejo_asesores.delete(documento);
				    break;
				    
			 case 5:
				 	id = JOptionPane.showInputDialog("Ingrese el ID de la Sede");
				 	while( !isNumeric(id) || Integer.parseInt(id) < manejo_sedes.select().size() )
				 		id = JOptionPane.showInputDialog("El ID ya se encuentre registrado o NO es valido");
				 	nombre = JOptionPane.showInputDialog("Ingrese el nombre de la Sede");
				 	manejo_sedes.insert(id, nombre);
				 	break;
				 	
			 case 6:
					 id = JOptionPane.showInputDialog("Ingrese el ID del cliente");
					 while( !isNumeric(id) || Integer.parseInt(id) < manejo_clientes.select().size() )
					 		id = JOptionPane.showInputDialog("El ID ya se encuentre registrado o NO es valido");
					 nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
					 manejo_clientes.insert(id, nombre);
					 break;
				    
			 default: opcion = 7;
			 		  break;
			 
			 }
		}while(opcion != 7);
		
	
	}
}
