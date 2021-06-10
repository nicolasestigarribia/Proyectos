package Hotel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ClasesDePersonas.Persona;
import Fecha.Fecha;

public class Hotel implements Serializable{

	public static int cantPisos;	//guardo el numero de Pisos y Habitaciones, así al crear un nuevo Piso/Habitación no necesito hacer un metodo para contar la cantidad de cada uno.
	public static ArrayList<TipoHabitacion> th;
	public static ArrayList<Habitacion> listaHabitaciones;
	
		static{
			cantPisos= 0;
			th= new ArrayList<>();
			listaHabitaciones= new ArrayList<>();
		}
	
	public static boolean agregarTH(TipoHabitacion auxTH){
			
		if(!encontradoTHEspecifico(auxTH.getNombre())){
			th.add(auxTH);	
			return true;
		}
		
		return false;
	}
	
	public static void cargarYagregarTH(){
		
		String s= "s";
		Scanner scan= new Scanner(System.in);
		
		while(s.equalsIgnoreCase("S")){
		
			TipoHabitacion h= new TipoHabitacion();
			h.cargarTipoHabitacion();
			
			agregarTH(h);
			
			System.out.println("\nQuiere ingresar un nuevo Tipo de Habitación? S/N ");
			s= scan.nextLine();
		}
	}
	
	//true si hay un tipo habitacion con ese mismo nombre
	public static boolean encontradoTHEspecifico(String n){
		
		for(TipoHabitacion aux : th){
			
			if(aux.igualTHEspecifico(n)){
				return true;
			}
		}

		return false;
	}
	
	//true si hay una habitacion con ese mismo numero
	public static boolean encontradoHabEspecifica(int n){
		
		for(Habitacion aux : listaHabitaciones){
			
			if(aux.getNumeroHab() == n){
				return true;
			}
		}

		return false;
	}

	public static void mostrarListaTH(){
		
		for(TipoHabitacion aux : th){
			aux.mostrarTipoHabitacion();
		}
	}
	
	public static void mostrarListaHab(){
		
		for(Habitacion aux : listaHabitaciones){
			aux.mostrarHabitacion();
		}
	}

	public static void ordenarHabxNumHabMenorMayor(){
		
		Collections.sort(listaHabitaciones, new Comparator<Habitacion>() {
            @Override
            public int compare(Habitacion h1, Habitacion h2) {

            	if(h1.getNumeroHab() > h2.getNumeroHab()){
        			return 1;
        		}
        		
        		else if(h1.getNumeroHab() < h2.getNumeroHab()){
        			return -1;
        		}
        		
                return 0;
            }
        });
    }

	public static void ordenarHabxNumHabMayorMenor(){
		
		Collections.sort(listaHabitaciones, new Comparator<Habitacion>() {
            @Override
            public int compare(Habitacion h1, Habitacion h2) {

            	if(h1.getNumeroHab() < h2.getNumeroHab()){
        			return 1;
        		}
        		
        		else if(h1.getNumeroHab() > h2.getNumeroHab()){
        			return -1;
        		}
        		
                return 0;
            }
        });
    }
	
	public static void ordenarTHxNombreMenorMayor(){
		
		Collections.sort(th, new Comparator<TipoHabitacion>() {
            @Override
            public int compare(TipoHabitacion th1, TipoHabitacion th2) {

                String izq= th1.getNombre();
                String der= th2.getNombre();

                return izq.compareTo(der);
            }
        });
    }
	
	public static void ordenarTHxNombreMayorMenor(){
		
		Collections.sort(th, new Comparator<TipoHabitacion>() {
            @Override
            public int compare(TipoHabitacion th1, TipoHabitacion th2) {

                String izq= th1.getNombre();
                String der= th2.getNombre();

                return der.compareTo(izq);
            }
        });
    }
	
	public static void ordenarTHxPrecioMenorMayor(){
		
		Collections.sort(th, new Comparator<TipoHabitacion>() {
            @Override
            public int compare(TipoHabitacion th1, TipoHabitacion th2) {

        		if(th1.getPrecio() > th2.getPrecio()){
        			return 1;
        		}
        		
        		else if(th1.getPrecio() < th2.getPrecio()){
        			return -1;
        		}
        		
                return 0;
            }
        });	
	}
	
	public static void ordenarTHxPrecioMayorMenor(){
		
		Collections.sort(th, new Comparator<TipoHabitacion>() {
            @Override
            public int compare(TipoHabitacion th1, TipoHabitacion th2) {

        		if(th1.getPrecio() > th2.getPrecio()){
        			return -1;
        		}
        		
        		else if(th1.getPrecio() < th2.getPrecio()){
        			return 1;
        		}
        		
                return 0;
            }
        });	
	}
	
	public static TipoHabitacion elegirTH(){
		
		int x=1;
		
		System.out.println("\nOpciones de Tipo de Habitación: ");
		for(TipoHabitacion aux : th){
			System.out.println("\t" + x + ") " + aux.getNombre());
			x++;
		}
		
		Scanner scan= new Scanner(System.in);
		System.out.print("Opción: ");
		int opcion= scan.nextInt();
		scan.nextLine();
		
		return th.get(opcion-1);
	}
	
	public static int elegirNumPiso(){

		Scanner scan= new Scanner(System.in);
		int opcion= -1;
		
		while(opcion == -1){
			
			if(cantPisos != 0){
				System.out.println("\n\nOpciones Número de Piso: ");
				for(int x=1; x <= cantPisos; x++){
					System.out.print(x + " - ");
				}
				
				System.out.print("\n\nOpción: ");
				opcion= scan.nextInt();
				scan.nextLine();
				
				if(encontrarNPElegido(opcion)){
					return opcion;	
				}
				
				else{
					System.out.println("\nEl número de Piso ingresado no se encuentra. ");
					opcion= -1;
				}
			}
			
			else{
				System.out.print("\nNo hay pisos cargados. Se creó un nuevo piso automáticamente. ");
				aumentarNumPiso();		
			}
		}
		
		return opcion;
	}
	
	public static boolean encontrarNPElegido(int n){
		
		for(int x=1; x <= cantPisos; x++){
			if(n == x){
				return true;
			}
		}
		
		return false;
	}
	public static void aumentarNumPiso(){
			cantPisos++;	
	}
	
	//@param Habitacion h, representa la habitacion que borrara
	public static boolean borrarUnaHabitacion(Habitacion h){	
		return listaHabitaciones.remove(h);
	}
	
	//@param int n, representa el numero de habitacion 
	public static int getPosHabitacionEspecifica(int n){	
		int x=0;
		
		for(Habitacion aux : listaHabitaciones){
			if(aux.getNumeroHab() == n){
				return x;
			}
			
			x++;
		}
		
		return -1;
	}


	//obtener la persona dependiendo del dni
	public static Persona getPasajeroListaPasajeros(Habitacion h, String d){
		
		for(Persona aux : h.getListaPasajeros()){
			if(aux.getDNI().equalsIgnoreCase(d)){
				return aux;
			}
		}
		
		return new Persona();
	}

	public static int getPosPasajeroListaPasajeros(Habitacion h, String d){
		
		int x=0;
		
		for(Persona aux : h.getListaPasajeros()){
			if(aux.getDNI().equalsIgnoreCase(d)){
				return x;
			}
			
			x++;
		}
		
		return -1;
	}
	
	//comprueba si hay alguna habitacion con el mismo numero que recibe por parametro
	public static boolean hayHabitacionEspecifica(int n){
		
		for(Habitacion aux : listaHabitaciones){
			if(aux.getNumeroHab() == n){
				return true;
			}
		}
		
		return false;
	}
	
	//@param TipoHabitacion h, representa el Tipo de Habitacion que borrara
	public static boolean borrarUnTipoHabitacion(TipoHabitacion h){	
		return th.remove(h);
	}
	
	//@param String n, representa el nombre del Tipo de Habitacion que va a borrar
	public static int getPosTipoHabEspecifica(String n){
		int x= 0;
		for(TipoHabitacion aux : th){
			if(aux.getNombre().equalsIgnoreCase(n)){
				return x;
			}
			
			x++;
		}
		
		return -1; //-1 si no se encuentra el dato					
	}
	
	//@param int x representa el numero de piso al que se busca
	public static boolean hayHabPisoEspecifico(int x){
		
		for(Habitacion aux : listaHabitaciones){
			if(aux.getNumPiso() == x){
				return true;
			}
		}
		
		return false;
	}
	
	public static void cargarJSON(){
		try{
			JSONObject op= new JSONObject();
			op.put("Cantidad de Pisos", Hotel.cantPisos);
			op.put("Lista Habitaciones", Hotel.listaHabitaciones);
			op.put("Lista Tipo Habitaciones", Hotel.th);
			
			FileWriter archivo= new FileWriter("archivo.json");
			archivo.write(op.toString());
			archivo.flush();
			archivo.close();
			
		}	catch(IOException ex){
				JOptionPane.showMessageDialog(null, "Error al cargar el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}	catch(JSONException js){
				JOptionPane.showMessageDialog(null, "Error en la carga de los objetos", "ERROR", JOptionPane.ERROR_MESSAGE);
				
		}
	}

	public static void leerJSON(){
		
		listaHabitaciones= new ArrayList<Habitacion>();
		th= new ArrayList<TipoHabitacion>();
		
		try{
			String contenido = new String(Files.readAllBytes(Paths.get("archivo.json")));
			JSONObject obj = new JSONObject(contenido);
		
			Hotel.cantPisos= obj.getInt("Cantidad de Pisos");
			JSONArray arregloHab= (JSONArray) obj.get("Lista Habitaciones");
			
			for(int x=0; x < arregloHab.length(); x++){
				JSONObject objHab= arregloHab.getJSONObject(x);
				JSONObject objTH= objHab.getJSONObject("tipoHab");
				
				JSONArray jaPas= objHab.getJSONArray("listaPasajeros");
				ArrayList<Persona> listaPas= new ArrayList<>();
				for(int y=0; y < jaPas.length(); y++){
					JSONObject pas= jaPas.getJSONObject(y);
					JSONObject nac= pas.getJSONObject("fecha");
					
					int dia= nac.getInt("dia");
					int mes= nac.getInt("mes");
					int anio= nac.getInt("anio");
					
					Persona pers= new Persona(pas.getString("nombre"), pas.getString("apellido"), pas.getString("DNI"), pas.getString("telefono"), pas.getString("nacionalidad"), new Fecha(dia, mes, anio));
					
					listaPas.add(pers);
				}
				
				TipoHabitacion tipoHab= new TipoHabitacion(objTH.getString("nombre"), objTH.getDouble("precio"), objTH.getInt("limite"));
				Habitacion hab= new Habitacion(objHab.getInt("numeroHab"), objHab.getInt("numPiso"), tipoHab, listaPas, objHab.getBoolean("disponibilidad"));
				
				listaHabitaciones.add(hab);
			}
			
			JSONArray arregloTH= (JSONArray) obj.get("Lista Tipo Habitaciones");
			
			for(int x=0; x < arregloTH.length(); x++){
				JSONObject objTH= arregloTH.getJSONObject(x);
				
				TipoHabitacion tipoHab= new TipoHabitacion(objTH.getString("nombre"), objTH.getDouble("precio"), objTH.getInt("limite"));
				Hotel.th.add(tipoHab);
			}
			
		}	catch(IOException ex){
			JOptionPane.showMessageDialog(null, "Error en la lectura.", "ERROR", JOptionPane.ERROR_MESSAGE);
		
		}	catch(JSONException js){
			JOptionPane.showMessageDialog(null, js.getStackTrace(), "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
	}
}