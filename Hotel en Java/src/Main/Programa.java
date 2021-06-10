package Main;

import Ventanas.VentanaPrincipal;
import Hotel.Hotel;

public class Programa {

	public static void main(String[] args){
		
		Hotel.leerJSON();
		
		VentanaPrincipal frame= new VentanaPrincipal();
		frame.setVisible(true);
	}
}
