package Ventanas;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Hotel.Hotel;
import Hotel.TipoHabitacion;
import Tabla.MiTabla;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaCargaTipoHabitacion extends JFrame {

	private JPanel contentPane;
	private JPanel panelInfo;
	private JPanel panelBotones;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldLimite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCargaTipoHabitacion frame = new VentanaCargaTipoHabitacion();
					frame.cargaTipoHabitacion();
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
	public VentanaCargaTipoHabitacion() {
		cargaTipoHabitacion();
	}
	
	public void cargaTipoHabitacion(){

		setTitle("Carga Tipo Habitaci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{194, 0};
		gbl_contentPane.rowHeights = new int[]{133, 32, 38, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panelInfo = new JPanel();
		GridBagConstraints gbc_panelInfo = new GridBagConstraints();
		gbc_panelInfo.fill = GridBagConstraints.BOTH;
		gbc_panelInfo.insets = new Insets(0, 0, 5, 0);
		gbc_panelInfo.gridx = 0;
		gbc_panelInfo.gridy = 0;
		contentPane.add(panelInfo, gbc_panelInfo);
		panelInfo.setLayout(new GridLayout(0, 2, 2, 2));
		
		JLabel labelNombre = new JLabel("Nombre: ");
		panelInfo.add(labelNombre);
		
		textFieldNombre = new JTextField();
		panelInfo.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel labelPrecio = new JLabel("Precio por día: ");
		panelInfo.add(labelPrecio);
		
		textFieldPrecio = new JTextField();
		panelInfo.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JLabel labelLimite = new JLabel("L\u00EDmite de Personas: ");
		panelInfo.add(labelLimite);
		
		textFieldLimite = new JTextField();
		panelInfo.add(textFieldLimite);
		textFieldLimite.setColumns(10);
		
		panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 2;
		contentPane.add(panelBotones, gbc_panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 2, 2));
		
		JButton botonAceptar = new JButton("Aceptar\r\n");
		panelBotones.add(botonAceptar);
		
		JButton botonCancelar = new JButton("Cancelar\r\n\r\n");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelBotones.add(botonCancelar);
		botonAceptar.addActionListener(new ActionListener() {
			String n= "";
			double p= 0;
			int l= 0;
			
			public void actionPerformed(ActionEvent arg0) {
				try{
					n= textFieldNombre.getText();
					p= Double.parseDouble(textFieldPrecio.getText());
					l= Integer.parseInt(textFieldLimite.getText());
				
					if(Hotel.encontradoTHEspecifico(n)){
						JOptionPane.showMessageDialog(null, "Ya existe un tipo de habitación con este nombre.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					else{
						Hotel.th.add(new TipoHabitacion(n, p, l));
						MiTabla.tablaTH.setModel(MiTabla.cargarInfoTablaTH());
						Hotel.cargarJSON();
					}
					
				}	catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}	finally{
					textFieldNombre.setText("");
					textFieldPrecio.setText("");
					textFieldLimite.setText("");
					
				}
			}
		});
	}
	
	public JPanel getPanelInfo(){
		return panelInfo;
	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public void setTextFieldNombre(JTextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}

	public JTextField getTextFieldPrecio() {
		return textFieldPrecio;
	}

	public void setTextFieldPrecio(JTextField textFieldPrecio) {
		this.textFieldPrecio = textFieldPrecio;
	}

	public JTextField getTextFieldLimite() {
		return textFieldLimite;
	}

	public void setTextFieldLimite(JTextField textFieldLimite) {
		this.textFieldLimite = textFieldLimite;
	}
}
