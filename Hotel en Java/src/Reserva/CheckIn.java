package Reserva;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;

import ClasesDePersonas.Persona;
import Fecha.Fecha;
import Hotel.Habitacion;
import Ventanas.VentanaCargaHabitacion;

public class CheckIn {
	
	private Persona ingresada;
	private Habitacion reservar;
	private Fecha inicio;
	private Fecha fin;
	
	public CheckIn(){
		ingresada= new Persona();
		reservar= new Habitacion();
		inicio= new Fecha();
		fin= new Fecha();
	}
	
	public CheckIn(Persona aux, Habitacion nueva, Fecha ini, Fecha FIN){
		ingresada= aux;
		reservar= nueva;
		inicio= ini;
		fin= FIN;
	}
	
	///AL LLEGAR LA FECHA DE LA RESERVA INGRESA EL PASAJERO EN LA HABITACION CON EL CAMBIO DE DISPONIBILIDAD
	public void IniciarReserva(){
		
		Calendar diaActual = Calendar.getInstance();
		int dia= diaActual.get(Calendar.DATE);
		int mes= diaActual.get(Calendar.MONTH)+1;
		int anio= diaActual.get(Calendar.YEAR);
		
		if((inicio.getDia()== dia)&&(inicio.getMes()== mes)&&(inicio.getAnio()==anio)){
			
			reservar.agregarPasajeroToListaPasajeros(ingresada);
			reservar.setDisponibilidad(false);
		}
	}
	
	///AL TERMINAR LA RESERVA SACA EL PASAJERO DE LA HABITACION Y CAMBIA LA DISPONIBILIDAD A DISPONIBLE
	public void TerminarReserva(){

		Calendar diaActual = Calendar.getInstance();
		int dia= diaActual.get(Calendar.DATE);
		int mes= diaActual.get(Calendar.MONTH)+1;
		int anio= diaActual.get(Calendar.YEAR);
		
		if((fin.getDia()== dia)&&(fin.getMes()== mes)&&(fin.getAnio()==anio)){
			reservar.borrarPasajeroDeListaPasajero(ingresada);
			reservar.setDisponibilidad(true);
		}
	}	
}
