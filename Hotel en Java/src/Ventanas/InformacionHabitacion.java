package Ventanas;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasesDePersonas.Persona;
import Hotel.Hotel;
import Hotel.TipoHabitacion;
import Tabla.MiTabla;

public class InformacionHabitacion extends JFrame {

	private JPanel contentPane;
	private JPanel panelBotones;
	private VentanaCargaHabitacion vch;
	public static JDialog ventanaLista;
	public static JDialog ventanaModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformacionHabitacion frame = new InformacionHabitacion(4);
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
	public InformacionHabitacion(int pos) {

		setTitle("Modificar Habitación");
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
		
		vch= new VentanaCargaHabitacion();						
		vch.getTextFieldNumHab().setText(String.valueOf(Hotel.listaHabitaciones.get(pos).getNumeroHab()));
		vch.getComboBoxPiso().setSelectedItem(Hotel.listaHabitaciones.get(pos).getNumPiso());
		vch.getComboBoxTipoHab().setSelectedItem(String.valueOf(Hotel.listaHabitaciones.get(pos).getTipoHab().getNombre()));
		
		JButton btnListaPasajeros = new JButton("Lista Pasajeros");
		btnListaPasajeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Hotel.listaHabitaciones.get(pos).getDisponibilidad()){
					JOptionPane.showMessageDialog(null, "Realizar el check in primero.", null, JOptionPane.INFORMATION_MESSAGE);
				}
				
				else{
					JDialog emergenteLista= new JDialog();
					emergenteLista.setTitle("Lista de Pasajeros");
					emergenteLista.setLocation(500, 250);
					
					emergenteLista.add(menuListaPasajeros(pos).getContentPane());
					
					emergenteLista.pack();
					emergenteLista.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_btnListaPasajeros = new GridBagConstraints();
		gbc_btnListaPasajeros.insets = new Insets(0, 0, 5, 0);
		gbc_btnListaPasajeros.gridx = 0;
		gbc_btnListaPasajeros.gridy = 1;
		contentPane.add(btnListaPasajeros, gbc_btnListaPasajeros);
		
		JPanel panelHab= vch.getPanelInfo();
		GridBagConstraints gbc_panelInfo = new GridBagConstraints();
		gbc_panelInfo.fill = GridBagConstraints.BOTH;
		gbc_panelInfo.insets = new Insets(0, 0, 5, 0);
		gbc_panelInfo.gridx = 0;
		gbc_panelInfo.gridy = 0;
		contentPane.add(panelHab, gbc_panelInfo);
		
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
			int n= 0;
			int p= 0;
			TipoHabitacion auxTH;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p= Integer.parseInt(vch.getComboBoxPiso().getSelectedItem().toString());
				auxTH= vch.getMapeo().get(vch.getComboBoxTipoHab().getSelectedItem());
				
				if(!vch.getTextFieldNumHab().getText().isEmpty()){				//condicion de que no este vacio el numero de la habitacion
					try{
						n= Integer.parseInt(vch.getTextFieldNumHab().getText());
							
						if(n == Hotel.listaHabitaciones.get(pos).getNumeroHab()){		//condicion cuando se quiere modificar cualquier dato, menos el numero.	
				
							Hotel.listaHabitaciones.get(pos).setNumPiso(p);
							Hotel.listaHabitaciones.get(pos).setTipoHab(auxTH);
							
							JOptionPane.showMessageDialog(null, "Habitación modificada.", null, JOptionPane.INFORMATION_MESSAGE);
						}
						
						else{															//condicion cuando quiere modificar todos los datos
							if(!Hotel.encontradoHabEspecifica(n)){
								Hotel.listaHabitaciones.get(pos).setNumeroHab(n);
								Hotel.listaHabitaciones.get(pos).setNumPiso(p);
								Hotel.listaHabitaciones.get(pos).setTipoHab(auxTH);
								
								JOptionPane.showMessageDialog(null, "Habitación modificada.", null, JOptionPane.INFORMATION_MESSAGE);
							}
							
							else{												//si se cumple la anterior condicion del else. y ya existe una habitacion con el mismo numero que se quiere guardar
								JOptionPane.showMessageDialog(null, "Ya existe una habitación con ese número.", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}

						Hotel.cargarJSON();
						MiTabla.tablaHabitacion.setModel(MiTabla.cargarInfoTablaHab());
						
						MiTabla.jdHab.dispose();
					
					}	catch(NumberFormatException nfe){				//cuando se ingresa una letra en un casillero de ingreso de unicamente numeros.
							JOptionPane.showMessageDialog(null, "No se admiten letras en los casilleros para ingresar números.", "ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				
				else{				//el else de la condicion if(!vch.getTextFieldNumHab().getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Debe llenar todos los casilleros.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}	
		});

		JButton botonCancelar= new JButton("Cancelar");
		panelBotones.add(botonCancelar);
		botonCancelar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MiTabla.jdHab.dispose();
			}
		});
		
		pack();
	}
	
	public JFrame menuListaPasajeros(int pos){
		JFrame nuevaVentana= new JFrame();
		nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nuevaVentana.setTitle("Lista Pasajeros");
		nuevaVentana.setBounds(100, 100, 615, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		nuevaVentana.setContentPane(contentPane);
		
		MiTabla.crearTablaListaPasajeros(pos);
		JScrollPane scrollPaneListaPas= new JScrollPane(MiTabla.tablaListaPasajeros);
		
		JButton btnAgregar = new JButton("<html><center><p>Agregar</p><center><p>Pasajero</p></html>");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Hotel.listaHabitaciones.get(pos).getListaPasajeros().size() != Hotel.listaHabitaciones.get(pos).getTipoHab().getLimite()){
					ventanaLista= new JDialog();
					VentanaCargaPersona vcp= new VentanaCargaPersona(pos);
					
					ventanaLista.getContentPane().add(vcp.getContentPane());
					ventanaLista.setTitle("Carga de Persona");
					ventanaLista.setLocation(500, 250);
					
					ventanaLista.pack();
					ventanaLista.setVisible(true);
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Se llego al maximo de persona permitidas. (max " + Hotel.listaHabitaciones.get(pos).getTipoHab().getLimite() + ").", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnModificar = new JButton("<html><center><p>Modificar</p><center><p>Pasajero</p></html>");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int posFila= MiTabla.tablaListaPasajeros.getSelectedRow();
				
				if(posFila != -1){
					ventanaModificar= new JDialog();
					DefaultTableModel model= (DefaultTableModel) MiTabla.tablaListaPasajeros.getModel();
					
					String dni= (String) model.getValueAt(posFila, 2);	
					
					int posPas= Hotel.getPosPasajeroListaPasajeros(Hotel.listaHabitaciones.get(pos), dni);
					
					ventanaModificar.setTitle("Modificar Habitación");
					ventanaModificar.setLocation(500, 250);
					ventanaModificar.add(new InformacionCargaPersona(pos, posPas).getContentPane());
					
					ventanaModificar.pack();
					ventanaModificar.setVisible(true);		
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Primero seleccione una fila.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton btnEliminar = new JButton("<html><center><p>Eliminar</p><center><p>Pasajero</p></html>");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int posFila= MiTabla.tablaListaPasajeros.getSelectedRow();
				if(posFila != -1){
					DefaultTableModel model= (DefaultTableModel) MiTabla.tablaListaPasajeros.getModel();
					
					String aux= (String) model.getValueAt(posFila, 2);
					Persona pasaj= Hotel.getPasajeroListaPasajeros(Hotel.listaHabitaciones.get(pos), aux);			//busca por el dni
					
					if(Hotel.listaHabitaciones.get(pos).borrarPasajeroDeListaPasajero(pasaj)){
						model.removeRow(posFila);
						
						Hotel.cargarJSON();
						MiTabla.tablaHabitacion.setModel(MiTabla.cargarInfoTablaHab());
					}
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPaneListaPas, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(92)
					.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(95, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPaneListaPas, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);
		
		return nuevaVentana;
	}
}
