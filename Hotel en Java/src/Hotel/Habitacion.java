package Hotel;

import java.io.Serializable;
import java.util.ArrayList;

import ClasesDePersonas.Persona;

public class Habitacion implements Serializable{

	private int numeroHab, numPiso;							
	private TipoHabitacion tipoHab;
	private ArrayList<Persona> listaPasajeros;
	private boolean disponibilidad;			//true si es disponible, false si esta ocupado (Trabaja con la situación actual de la habitación)
	
		public Habitacion(){
			
			numeroHab= 0;
			numPiso= 0;
			listaPasajeros= new ArrayList<>();
			disponibilidad= true;					//al crear una habitación comienza estando disponible
		}

		public Habitacion(int nH, int nP, TipoHabitacion t){
			
			numeroHab= nH;
			numPiso= nP;
			tipoHab= t;
			listaPasajeros= new ArrayList<>();
			disponibilidad= true;	
		}

		public Habitacion(int nH, int nP, TipoHabitacion t, ArrayList<Persona> p, boolean d){
			
			numeroHab= nH;
			numPiso= nP;
			tipoHab= t;
			listaPasajeros= p;
			disponibilidad= d;	
		}

	public int getNumeroHab() {
		return numeroHab;
	}

	public void setNumeroHab(int numeroHab) {
		this.numeroHab = numeroHab;
	}

	public TipoHabitacion getTipoHab() {
		return tipoHab;
	}

	public void setTipoHab(TipoHabitacion tipoHab) {
		this.tipoHab = tipoHab;
	}
	
	public ArrayList<Persona> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(ArrayList<Persona> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
		
		if(!listaPasajeros.isEmpty()){
			disponibilidad= false;
		}
	}

	public boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public int getNumPiso() {
		return numPiso;
	}

	public void setNumPiso(int numPiso) {
		this.numPiso = numPiso;
	}

	
	public void agregarPasajeroToListaPasajeros(Persona p){
		
		if(tipoHab.getLimite() != listaPasajeros.size()){
			listaPasajeros.add(p);
			disponibilidad= false;
		}
		
		else{
			System.out.println("\nSe llegó al límite de Personas permitidas en la Habitación. (máx " + tipoHab.getLimite() + ")");
		}
	}
	
	public Persona getPasajeroDeListaPasajeros(Persona p){
		
		for(Persona aux : listaPasajeros){
			if(aux.equals(p)){
				return p;
			}
		}
		
		return new Persona();
	}
	
	public boolean borrarPasajeroDeListaPasajero(Persona p){
		boolean resultado= listaPasajeros.remove(p);
		
		if(listaPasajeros.isEmpty()){
			disponibilidad= true;
		}
		
		return resultado;
	}
	
	public void mostrarHabitacion(){

		System.out.println("\nNúmero Habitación: " + numeroHab);
		System.out.println("Número Piso: " + numPiso);
		System.out.println("Tipo de Habitación: " + tipoHab.getNombre());
		System.out.println("Disponibilidad: " + disponibilidad);
	}
	
	public void mostrarListaPasajerosHabitacion(){
		
		for(Persona p : listaPasajeros){
			p.mostrarPersona();
		}
	}
	
}