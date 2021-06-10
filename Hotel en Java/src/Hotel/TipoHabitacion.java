package Hotel;

import java.io.Serializable;
import java.util.Scanner;

public class TipoHabitacion implements Serializable{

	private String nombre;
	private double precio;
	private int limitePersonas;		//cuantas persona entraran como máximo en esa habitación
		
		public TipoHabitacion(){
			
			nombre= "";
			precio= 0;
			limitePersonas= 0;
		}
		public TipoHabitacion(String n, double p, int l){
			
			nombre= n;
			precio= p;
			limitePersonas= l;
		}
		
	public String getNombre(){
		return nombre;
	}
	
	public double getPrecio(){
		return precio;
	}
	
	public int getLimite(){
		return limitePersonas;
	}
	
	public void setNombre(String n){
		nombre= n;
	}
	
	public void setPrecio(double p){
		precio= p;
	}
	
	public void setLimite(int l){
		limitePersonas= l;
	}
	
	public void cargarTipoHabitacion(){
		
		Scanner scan= new Scanner(System.in);
		System.out.print("\nIngrese nombre: ");
		nombre= scan.nextLine();
		System.out.print("Ingrese precio: ");
		precio= scan.nextDouble();
		System.out.print("Ingrese Límite Personas: ");
		limitePersonas= scan.nextInt();
		scan.nextLine();
	}
	
	public void mostrarTipoHabitacion(){

		System.out.println("\nNombre: " + nombre);
		System.out.println("Precio: " + precio);
		System.out.println("Límite Personas: " + limitePersonas);
	}
	
	public boolean igualTHEspecifico(String n){
	
		if(nombre.equalsIgnoreCase(n)){
			return true;
		}
		
		return false;
	}
	
	
}