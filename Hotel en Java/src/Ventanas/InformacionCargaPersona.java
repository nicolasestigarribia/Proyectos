package Ventanas;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ClasesDePersonas.Persona;
import Fecha.Fecha;
import Hotel.Hotel;
import Tabla.MiTabla;

public class InformacionCargaPersona extends JFrame {

	private JPanel contentPane;
	private JPanel panelInfo;
	private JPanel panelBotones;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAnio;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDNI;
	private JTextField textFieldTelefono;
	private JTextField textFieldNacionalidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformacionCargaPersona frame = new InformacionCargaPersona(0, 0);
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
	public InformacionCargaPersona(int posHab, int posPas) {
		setBounds(new Rectangle(500, 250, 500, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Carga Persona");
		contentPane= new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{194};
		gbl_contentPane.rowHeights = new int[]{183, 32, 38};
		gbl_contentPane.columnWeights = new double[]{};
		gbl_contentPane.rowWeights = new double[]{};
		contentPane.setLayout(gbl_contentPane);
		
		Persona pasAux= Hotel.listaHabitaciones.get(posHab).getListaPasajeros().get(posPas);
		
		panelInfo= new JPanel();
		GridBagConstraints gbc_panelInfo = new GridBagConstraints();
		gbc_panelInfo.fill = GridBagConstraints.BOTH;
		gbc_panelInfo.insets = new Insets(0, 0, 5, 0);
		gbc_panelInfo.gridx = 0;
		gbc_panelInfo.gridy = 0;
		contentPane.add(panelInfo, gbc_panelInfo);
		panelInfo.setLayout(new GridLayout(0, 2, 2, 2));
		
		JLabel lblNombre = new JLabel("Nombre: ");
		textFieldNombre = new JTextField(pasAux.getNombre());
		textFieldNombre.setColumns(10);
		panelInfo.add(lblNombre);
		panelInfo.add(textFieldNombre);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		textFieldApellido = new JTextField(pasAux.getApellido());
		textFieldApellido.setColumns(10);
		panelInfo.add(lblApellido);
		panelInfo.add(textFieldApellido);
		
		JLabel lblDnicuit = new JLabel("DNI/CUIT: ");
		textFieldDNI = new JTextField(pasAux.getDNI());
		textFieldDNI.setColumns(10);
		panelInfo.add(lblDnicuit);
		panelInfo.add(textFieldDNI);
		
		JLabel lblTelefonoFijo = new JLabel("Tel\u00E9fono: ");
		textFieldTelefono = new JTextField(pasAux.getTelefono());
		textFieldTelefono.setColumns(10);
		panelInfo.add(lblTelefonoFijo);
		panelInfo.add(textFieldTelefono);
		
		JPanel panelFechaNacimiento = new JPanel();
		
		textFieldDia = new JTextField(2);
		textFieldDia.setText(String.valueOf(pasAux.getFecha().getDia()));
		
		textFieldMes = new JTextField(2);
		textFieldMes.setText(String.valueOf(pasAux.getFecha().getMes()));
		
		textFieldAnio = new JTextField(4);
		textFieldAnio.setText(String.valueOf(pasAux.getFecha().getAnio()));
		
		panelFechaNacimiento.add(textFieldDia);
		JLabel label = new JLabel("/");
		panelFechaNacimiento.add(label);
		panelFechaNacimiento.add(textFieldMes);
		JLabel label_1 = new JLabel("/");
		panelFechaNacimiento.add(label_1);
		panelFechaNacimiento.add(textFieldAnio);
		panelFechaNacimiento.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelFechaNacimiento.add(textFieldDia);
		panelFechaNacimiento.add(label);
		panelFechaNacimiento.add(textFieldMes);
		panelFechaNacimiento.add(label_1);
		panelFechaNacimiento.add(textFieldAnio);
		
		JLabel fechaNacimiento = new JLabel("Fecha de Nacimiento: ");
		panelInfo.add(fechaNacimiento);
		panelInfo.add(panelFechaNacimiento);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad: ");
		textFieldNacionalidad = new JTextField(pasAux.getNacionalidad());
		textFieldNacionalidad.setColumns(10);
		panelInfo.add(lblNacionalidad);
		panelInfo.add(textFieldNacionalidad);
		
		panelBotones= new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 2;
		contentPane.add(panelBotones, gbc_panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 2, 2));
		
		JButton botonAceptar = new JButton("Aceptar");
		
		//guardara los datos cargados en las casillas en una variable de tipo Persona
		botonAceptar.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) {
				
				String n= textFieldNombre.getText();
				String a= textFieldApellido.getText();
				String d= textFieldDNI.getText();
				String t= textFieldTelefono.getText();
				String nacio= textFieldNacionalidad.getText();
				int dia= 0, mes= 0, anio= 0;
				
				try{	
					dia= Integer.parseInt(textFieldDia.getText());
					mes= Integer.parseInt(textFieldMes.getText());
					anio= Integer.parseInt(textFieldAnio.getText());

					pasAux.setNombre(n);
					pasAux.setApellido(a);
					pasAux.setDNI(d);
					pasAux.setTelefono(t);
					pasAux.setNacionalidad(nacio);
					pasAux.setFecha(new Fecha(dia, mes, anio));
					
					Hotel.listaHabitaciones.get(posHab).getListaPasajeros().set(posPas, pasAux);
					Hotel.cargarJSON();
					
					MiTabla.tablaListaPasajeros.setModel(MiTabla.cargarInfoTablaListaPasajeros(posHab));
				}	catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}		
			}
		});
		
		panelBotones.add(botonAceptar);
		
		JButton botonCancelar = new JButton("Cancelar\r\n");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InformacionHabitacion.ventanaModificar.dispose();
			}
		});
		
		panelBotones.add(botonCancelar);
		
		pack();
	}

	public JPanel getContentPane() {
		return contentPane;
	}

}
