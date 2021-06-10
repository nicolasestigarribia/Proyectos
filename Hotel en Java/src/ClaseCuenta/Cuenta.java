package ClaseCuenta;

import java.io.Serializable;
import java.util.Scanner;
import ClasesDePersonas.Persona;

public class Cuenta implements Serializable{
	private String Usuario, Contrasenia;
	private int tipoCuenta;
	private Persona nueva;
	
	///CONSTRUCTORES
	public Cuenta(){
		Usuario=" ";
		Contrasenia=" ";
		tipoCuenta= 0;
		nueva= new Persona();
	}
	
	public Cuenta(String usu, String contra, int tipo , Persona aux){
		Usuario= usu;
		Contrasenia= contra;
		tipoCuenta= tipo;
		nueva= aux;
	}
	
	public Cuenta(Cuenta aux){
		Usuario= aux.Usuario;
		Contrasenia= aux.Contrasenia;
		tipoCuenta=  aux.tipoCuenta;
		nueva= aux.getPersona();
	}
	
	public int getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	///GETHERS Y SETHERS
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getContrasenia() {
		return Contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		Contrasenia = contrasenia;
	}
	
	public void setPersona(Persona aux){
		nueva= aux;
	}
	
	public Persona getPersona(){
		return nueva;
	}
}
