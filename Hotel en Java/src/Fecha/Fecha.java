package Fecha;

import java.io.Serializable;

public class Fecha implements Serializable{

	private int dia, mes, anio;
	
		public Fecha(){
			dia= 0;
			mes= 0;
			anio= 0;
		}
		
		public Fecha(int d, int m, int a){
			dia= d;
			mes= m;
			anio= a;
		}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
}
