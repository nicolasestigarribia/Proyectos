package ClasesDePersonas;
import java.io.Serializable;

import Fecha.Fecha;


public class Persona implements Serializable{
	
	private String nombre, apellido, dni, telefono, nacionalidad;
	private Fecha nacimiento;
	
	///CONSTRUCTORES
		public Persona(){
			nombre= " ";
			apellido= " ";
			dni= " ";
			telefono= " ";
			nacionalidad= " ";
			nacimiento= new Fecha();
		}
		
		public Persona(String n, String a, String d, String t, String nac, int dia, int mes, int anio){
			
			nombre= n;
			apellido= a;
			dni= d;
			telefono= t;
			nacionalidad= nac;
			nacimiento= new Fecha(dia, mes, anio);
		}
		
		public Persona(String n, String a, String d, String t, String nac, Fecha f){
			
			nombre= n;
			apellido= a;
			dni= d;
			telefono= t;
			nacionalidad= nac;
			nacimiento= f;
		}
		
		public Persona(Persona nueva){
			nombre= nueva.getNombre();
			apellido= nueva.getApellido();
			dni= nueva.getDNI();
			telefono= nueva.getTelefono();
			nacionalidad= nueva.getNacionalidad();
			nacimiento= nueva.getFecha();
		}
	
	///GETHERS
	public String getNombre(){
		return nombre;
	}
	
	public String getApellido(){
		return apellido;
	}
	
	public String getDNI(){
		return dni;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public Fecha getFecha(){
		return nacimiento;
	}
	
	public String getFechaEntera(){
		return nacimiento.getDia() + "/" + nacimiento.getMes() + "/" + nacimiento.getAnio();
	}
	
	public String getNacionalidad(){
		return nacionalidad;
	}
	
	///SETHERS
	public void setNombre(String n){
		nombre= n;
	}
	
	public void setApellido(String a){
		apellido= a;
	}
	
	public void setDNI(String dn){
		dni= dn;
	}
	
	public void setTelefono(String tel){
		telefono= tel;
	}
	
	public void setFecha(Fecha p){
		nacimiento= p;
	}
	
	public void setNacionalidad(String nac){
		nacionalidad= nac;
	}
	
	///MOSTRAR PERSONA
	public void mostrarPersona(){
		
		System.out.println("\nNombre: " + nombre);
		System.out.println("Apellido: " + apellido);
		System.out.println("DNI/CUIT: " + dni);
		System.out.println("Teléfono Fijo: " + telefono);
		System.out.println("Fecha: " + getFechaEntera());
		System.out.println("Nacionalidad: " + nacionalidad);
	
	}

}
