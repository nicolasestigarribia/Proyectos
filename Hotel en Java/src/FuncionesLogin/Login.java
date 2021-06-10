package FuncionesLogin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ClasesDePersonas.Persona;
import Fecha.Fecha;
import Hotel.Habitacion;
import Hotel.Hotel;
import Hotel.TipoHabitacion;
import Ventanas.VentanaPrincipal;
import ClaseCuenta.Cuenta;

import ClasesDePersonas.Persona;

public class Login implements Serializable {
	
	public static ArrayList<Cuenta> listaCuentas;
	
	static{
		listaCuentas= new ArrayList<>();
	}

	///COMPROBAR SI LA CUENTA 
	public static boolean ComprobarCuentaLista(String usu, String contra){
		
		leerJSONCuenta();
		
		for(Cuenta aux: listaCuentas){
			if((aux.getUsuario().equals(usu))&&(aux.getContrasenia().equals(contra))){
				return true;
			}
		}
		
		return false;
	}
	
	///INGRESAR CUENTA AL ARCHIVO
	public static void cargarJSONCuenta(){
		try{
			
			JSONObject op= new JSONObject();
			
			op.put("Lista Cuentas", listaCuentas);
			
			FileWriter archivo= new FileWriter("archivo_Cuentas.json");
			archivo.write(op.toString());
			archivo.flush();
			archivo.close();
			
		}	catch(IOException ex){
				JOptionPane.showMessageDialog(null, "Error al cargar el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}	catch(JSONException js){
				JOptionPane.showMessageDialog(null, "Error en la carga de los objetos", "ERROR", JOptionPane.ERROR_MESSAGE);
				
		}
	}

	///LEER ARCHIVO LISTA
	public static void leerJSONCuenta(){
		
		listaCuentas= new ArrayList<Cuenta>();		
		
		try{
			String contenido = new String(Files.readAllBytes(Paths.get("archivo_Cuentas.json")));
			JSONObject obj = new JSONObject(contenido);
		
			
			JSONArray arregloHab= (JSONArray) obj.get("Lista Cuentas");
			
			for(int x=0; x < arregloHab.length(); x++){
				JSONObject objCuenta= arregloHab.getJSONObject(x);
				
				JSONObject persona= objCuenta.getJSONObject("persona");
				JSONObject nac= persona.getJSONObject("fecha");
					
				int dia= nac.getInt("dia");
				int mes= nac.getInt("mes");
				int anio= nac.getInt("anio");
					
				Persona pers= new Persona(persona.getString("nombre"), persona.getString("apellido"), persona.getString("DNI"), persona.getString("telefono"), persona.getString("nacionalidad"), new Fecha(dia, mes, anio));
					
				Cuenta nueva= new Cuenta(objCuenta.getString("usuario"), objCuenta.getString("contrasenia"), objCuenta.getInt("tipoCuenta"), pers);
				
				listaCuentas.add(nueva);
			}
			
		}	catch(IOException ex){
			JOptionPane.showMessageDialog(null, "Error en la lectura.", "ERROR", JOptionPane.ERROR_MESSAGE);
		
		}	catch(JSONException js){
			JOptionPane.showMessageDialog(null, js.getStackTrace(), "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	public static Cuenta retornarDeterminadaCuenta(String usu, String contra){
		
		for(Cuenta aux: listaCuentas){
			if((aux.getUsuario().equals(usu))&&(aux.getContrasenia().equals(contra))){
				return aux;
			}
		}
		
		return null;
	}
	
	public static void agregarCuentaDefault(){
		
		Persona nuevo= new Persona("Lucas", "Garcia", "41106439", "2235263316", "Argentina", 27, 06, 1998);
		Cuenta nueva= new Cuenta("lucas-nahuel@live.com", "12345", 1, nuevo);
		
		listaCuentas.add(nueva);
		cargarJSONCuenta();
	}
}
