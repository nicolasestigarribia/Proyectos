package Ventanas;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Hotel.Habitacion;
import Hotel.Hotel;
import Hotel.TipoHabitacion;
import Tabla.MiTabla;

public class InformacionTipoHabitacion extends JFrame {

	private JPanel contentPane;
	private JPanel panelBotones;
	private VentanaCargaTipoHabitacion vcth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformacionTipoHabitacion frame = new InformacionTipoHabitacion(0);
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
	public InformacionTipoHabitacion(int pos) {

		setTitle("Modificar Tipo Habitación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{194, 0};
		gbl_contentPane.rowHeights = new int[]{133, 3, 38, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		vcth= new VentanaCargaTipoHabitacion();						//creo un objeto de la ventana para crear un tipo de habitacion, y utilizo sos datos para crear el JDialog
		vcth.getTextFieldNombre().setText(Hotel.th.get(pos).getNombre());
		vcth.getTextFieldPrecio().setText(String.valueOf(Hotel.th.get(pos).getPrecio()));
		vcth.getTextFieldLimite().setText(String.valueOf(Hotel.th.get(pos).getLimite()));
		
		JPanel panelTH= vcth.getPanelInfo();
		GridBagConstraints gbc_panelInfo = new GridBagConstraints();
		gbc_panelInfo.fill = GridBagConstraints.BOTH;
		gbc_panelInfo.insets = new Insets(0, 0, 5, 0);
		gbc_panelInfo.gridx = 0;
		gbc_panelInfo.gridy = 0;
		contentPane.add(panelTH, gbc_panelInfo);
		
		panelBotones= new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 2;
		contentPane.add(panelBotones, gbc_panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 2, 2));
		
		JButton botonModificar= new JButton("Modificar");
		panelBotones.add(botonModificar);
		botonModificar.addActionListener(new ActionListener(){
			String n= "";
			double p= 0;
			int l= 0;
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					n= vcth.getTextFieldNombre().getText();
					p= Double.parseDouble(vcth.getTextFieldPrecio().getText());
					l= Integer.parseInt(vcth.getTextFieldLimite().getText());
				
					if(!n.equals("")){
						
						if(n.equalsIgnoreCase(Hotel.th.get(pos).getNombre())){	//condicion cuando quiere modificar cualquier dato, menos el nombre
							Hotel.th.get(pos).setPrecio(p);
							Hotel.th.get(pos).setLimite(l);
							
							for(Habitacion auxHab : Hotel.listaHabitaciones){	//actualiza los datos de la tabla
								if(auxHab.getTipoHab().getNombre().equalsIgnoreCase(n)){
									auxHab.setTipoHab(Hotel.th.get(pos));
								}
							}
							
							JOptionPane.showMessageDialog(null, "Tipo de Habitación modificada.", null, JOptionPane.INFORMATION_MESSAGE);
						}
						
						else{
							if(!Hotel.encontradoTHEspecifico(n)){	//condicion cuando quiere modificar todos los datos
								Hotel.th.get(pos).setNombre(n);
								Hotel.th.get(pos).setPrecio(p);
								Hotel.th.get(pos).setLimite(l);

								for(Habitacion auxHab : Hotel.listaHabitaciones){				//elimina todos los TH modificados en las habitaciones 
									if(auxHab.getTipoHab().getNombre().equalsIgnoreCase(n)){
										auxHab.setTipoHab(new TipoHabitacion());
									}
								}
								
								JOptionPane.showMessageDialog(null, "Tipo de Habitación modificada.", null, JOptionPane.INFORMATION_MESSAGE);
							}
							
							else{									//cuando ya hay una habitacion con ese mismo nombre cumpliendose la condicion del else anterior.
								JOptionPane.showMessageDialog(null, "Ya existe un tipo de habitación con este nombre.", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
						
						Hotel.cargarJSON();
						//guardo y modifico los datos de los archivos y actualizo los datos de las tablas.
						MiTabla.tablaTH.setModel(MiTabla.cargarInfoTablaTH());
						MiTabla.tablaHabitacion.setModel(MiTabla.cargarInfoTablaHab());
						
						MiTabla.jdTH.dispose();
					}
					
					else{			//cuando deja casilleros vacios
						JOptionPane.showMessageDialog(null, "Debe llenar todos los casilleros.", "ERROR", JOptionPane.ERROR_MESSAGE);	
					}
					
				}	catch(NumberFormatException nfe){		//si ingresa una letra en los casilleros de carga de unicamente numeros.
						JOptionPane.showMessageDialog(null, "No se admiten letras en los casilleros para ingresar números.", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}		
			}	
		});

		JButton botonCancelar= new JButton("Cancelar");
		panelBotones.add(botonCancelar);
		botonCancelar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiTabla.jdTH.dispose();
			}
		});
		
		pack();
	}
}
