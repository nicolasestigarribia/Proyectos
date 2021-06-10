package Ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Hotel.Hotel;
import Main.Programa;
import Tabla.MiTabla;

public class VentanaPrincipal extends JFrame{

	private JPanel contentPane;
	private JTable tablaHabitacion;
	private JTable tablaTH;
	private VentanaCargaHabitacion vch= new VentanaCargaHabitacion();
	private VentanaCargaTipoHabitacion vcth= new VentanaCargaTipoHabitacion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Programa.main(args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setTitle("Ventana Principal\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(95, 158, 160));
		
		JInternalFrame internalFrame = new JInternalFrame("Panel de Datos");
		internalFrame.setVisible(true);
		internalFrame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		internalFrame.setPreferredSize(new Dimension(55, 35));
		
		JScrollPane scrollPaneTablaHabitacion= new JScrollPane(MiTabla.tablaHabitacion);
		JScrollPane scrollPaneTablaTH= new JScrollPane(MiTabla.tablaTH);
		
		JButton botonSalir = new JButton("");
		botonSalir.setToolTipText("Salir y Guardar");
		botonSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Hotel.cargarJSON();
				System.exit(0);
			}
		});
		
		botonSalir.setContentAreaFilled(false);
		botonSalir.setBorderPainted(false);
		botonSalir.setBorder(null);
		botonSalir.setBackground(SystemColor.menu);
		botonSalir.setIcon(new ImageIcon("C:\\Users\\gianf\\Desktop\\TSP\\TrabajoFinal_HotelAuxiliar\\shutdown_64x64.png"));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(internalFrame, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonSalir)
						.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneTablaHabitacion, GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
						.addComponent(scrollPaneTablaTH, GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(botonSalir)
							.addGap(31)
							.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
							.addComponent(internalFrame, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPaneTablaHabitacion, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneTablaTH, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panelBotones.setLayout(new GridLayout(0, 2, 2, 2));
		
		JButton botonAgregarTH = new JButton("<html><center><p>Crear Nuevo</p><center><p>Tipo de Habitaci\u00F3n</p></html>");
		botonAgregarTH.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonAgregarTH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.setTitle("Carga Tipo de Habitación");
				vcth.cargaTipoHabitacion();
				internalFrame.setContentPane(vcth.getContentPane());
				internalFrame.setAlignmentX(Component.CENTER_ALIGNMENT);
				internalFrame.setAlignmentY(Component.CENTER_ALIGNMENT);
			}
		});
		botonAgregarTH.setHorizontalTextPosition(SwingConstants.CENTER);
		panelBotones.add(botonAgregarTH);
		
		JButton botonAgregarHab = new JButton("<html><center><p>Crear Nueva</p><center><p>Habitaci\u00F3n</p><html>");
		botonAgregarHab.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonAgregarHab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.setTitle("Carga Habitación");
				vch.cargaHabitacion();
				internalFrame.setContentPane(vch.getContentPane());
				internalFrame.setAlignmentX(Component.CENTER_ALIGNMENT);
				internalFrame.setAlignmentY(Component.CENTER_ALIGNMENT);
			}
		});
		panelBotones.add(botonAgregarHab);
		
		JButton botonAumentarPiso = new JButton("<html><center><p>Crear Nuevo</p><center><p>Piso</p><html>");
		botonAumentarPiso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hotel.aumentarNumPiso();
				JOptionPane.showMessageDialog(null, "Piso Nº" + Hotel.cantPisos + " creado.", null, JOptionPane.INFORMATION_MESSAGE);
				Hotel.cargarJSON();
			}
		});
		botonAumentarPiso.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(botonAumentarPiso);
		
		JButton botonEliminarPiso = new JButton("<html><center><p>Eliminar</p><center><p>\u00DAltimo Piso</p></html>");
		botonEliminarPiso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Hotel.hayHabPisoEspecifico(Hotel.cantPisos)){
					JOptionPane.showMessageDialog(null, "Piso Nº" + Hotel.cantPisos + " borrado", null, JOptionPane.INFORMATION_MESSAGE);	
					Hotel.cantPisos--;				
					Hotel.cargarJSON();
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Primero tiene que borrar todas las habitaciones del Piso Nº" + Hotel.cantPisos + " para este ser borrado", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonEliminarPiso.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(botonEliminarPiso);
		contentPane.setLayout(gl_contentPane);
	}	
}
