package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClaseCuenta.Cuenta;

import FuncionesLogin.Login;
import Hotel.Hotel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField insertarUsuario;
	private JPasswordField insertarContraseña;
	private static File archivoCuentas;
	
	static{
		archivoCuentas= new File("Archivo_Cuentas.dat");
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cuenta nueva= new Cuenta();
				nueva.setUsuario(insertarUsuario.getText());
				nueva.setContrasenia(insertarContraseña.getText());
				if(Login.ComprobarCuentaLista(nueva.getUsuario(), nueva.getContrasenia())==true){
					
					
					dispose();
					Hotel.leerJSON();
					if(Login.retornarDeterminadaCuenta(nueva.getUsuario(), nueva.getContrasenia()).getTipoCuenta()==1){
						VentanaPrincipal frame= new VentanaPrincipal();
						frame.setVisible(true);
					}
					else{
						
					}
					
				}
				else{
					 if(Login.ComprobarCuentaLista(nueva.getUsuario(), nueva.getContrasenia())==false){
						 JOptionPane.showMessageDialog(null, "El Usuario o la Contraseña son Incorrectos ", "ERROR", JOptionPane.ERROR_MESSAGE); 	
					 }
				}
				
			}
		});
		botonAceptar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		botonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton botonRegistrarse = new JButton("Registrarse");
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaRegistrarse frame = new VentanaRegistrarse();
				frame.setVisible(true);
			
			}});
		botonRegistrarse.setFont(new Font("Arial Black", Font.PLAIN, 13));
		
		JLabel usuario = new JLabel("Usuario");
		usuario.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JLabel contraseña = new JLabel("Contrase\u00F1a");
		contraseña.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		insertarUsuario = new JTextField();
		insertarUsuario.setColumns(10);
		
		insertarContraseña = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(usuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(contraseña, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(insertarUsuario, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(insertarContraseña, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
					.addGap(119))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(124)
					.addComponent(botonAceptar)
					.addGap(18)
					.addComponent(botonCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(136))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(146)
					.addComponent(botonRegistrarse)
					.addContainerGap(161, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(88)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(insertarUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(usuario))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(contraseña)
						.addComponent(insertarContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(botonRegistrarse)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(botonCancelar)
						.addComponent(botonAceptar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}