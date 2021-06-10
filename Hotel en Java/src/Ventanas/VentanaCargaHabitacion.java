package Ventanas;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Hotel.Habitacion;
import Hotel.Hotel;
import Hotel.TipoHabitacion;
import Tabla.MiTabla;

public class VentanaCargaHabitacion extends JFrame {

	private JPanel contentPane;
	private JPanel panelInfo;
	private JPanel panelBotones;
	private JTextField textFieldNumHab;
	private JComboBox comboBoxPiso;
	private JComboBox comboBoxTipoHab;
	private HashMap<String, TipoHabitacion> mapeo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCargaHabitacion frame = new VentanaCargaHabitacion();
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
	public VentanaCargaHabitacion() {
		cargaHabitacion();
	}

	public void cargaHabitacion(){
		setTitle("Carga Habitaci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{194};
		gbl_contentPane.rowHeights = new int[]{133, 43, 27};
		gbl_contentPane.columnWeights = new double[]{};
		gbl_contentPane.rowWeights = new double[]{};
		contentPane.setLayout(gbl_contentPane);
		
		mapeo = new HashMap<>();
		
		panelInfo = new JPanel();
		GridBagConstraints gbc_panelInfo = new GridBagConstraints();
		gbc_panelInfo.fill = GridBagConstraints.BOTH;
		gbc_panelInfo.insets = new Insets(0, 0, 5, 0);
		gbc_panelInfo.gridx = 0;
		gbc_panelInfo.gridy = 0;
		contentPane.add(panelInfo, gbc_panelInfo);
		panelInfo.setLayout(new GridLayout(0, 2, 2, 2));
		
		JLabel labelNumHab = new JLabel("N\u00FAmero de Habitaci\u00F3n: ");
		panelInfo.add(labelNumHab);
		
		textFieldNumHab = new JTextField("0");
		panelInfo.add(textFieldNumHab);
		textFieldNumHab.setColumns(10);
		
		JLabel labelNumPiso = new JLabel("N\u00FAmero de Piso: ");
		panelInfo.add(labelNumPiso);
		
		comboBoxPiso = new JComboBox();
		panelInfo.add(comboBoxPiso);
		
		for(int x=1; x <= Hotel.cantPisos; x++){
			comboBoxPiso.addItem(x);
		}
		
		JLabel labelTipoHab = new JLabel("Tipo de Habitaci\u00F3n: ");
		panelInfo.add(labelTipoHab);
		
		comboBoxTipoHab = new JComboBox();
		DefaultComboBoxModel modeloTipoHab= new DefaultComboBoxModel();
		
		for(TipoHabitacion aux : Hotel.th){
			modeloTipoHab.addElement(aux.getNombre());
			mapeo.put(aux.getNombre(), aux);
		}
		
		comboBoxTipoHab.setModel(modeloTipoHab);
		panelInfo.add(comboBoxTipoHab);
		
		panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 2;
		contentPane.add(panelBotones, gbc_panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 2, 2));
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			int numHab=0, numPiso=0;
			TipoHabitacion tipoHab= new TipoHabitacion();
			
			public void actionPerformed(ActionEvent arg0) {
				numPiso= Integer.parseInt(comboBoxPiso.getSelectedItem().toString());
				tipoHab= mapeo.get(comboBoxTipoHab.getSelectedItem());

				if(!textFieldNumHab.getText().isEmpty()){
			
					try{
						numHab= Integer.parseInt(textFieldNumHab.getText());
						
						if(Hotel.hayHabitacionEspecifica(numHab)){
							JOptionPane.showMessageDialog(null, "Ya existe una habitación con este número.", "ERROR", JOptionPane.ERROR_MESSAGE);	
						}
						
						else{
							Hotel.listaHabitaciones.add(new Habitacion(numHab, numPiso, tipoHab));
							MiTabla.tablaHabitacion.setModel(MiTabla.cargarInfoTablaHab());
							Hotel.cargarJSON();
						}
						
					}	catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);	
						
					}	finally{
						textFieldNumHab.setText("");
						comboBoxPiso.setSelectedIndex(0);
						comboBoxTipoHab.setSelectedIndex(0);
					}
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Hay casilleros vacios.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panelBotones.add(botonAceptar);
		
		JButton botonCancelar = new JButton("Cancelar");
		panelBotones.add(botonCancelar);
	}
	
	public JPanel getContentPanel() {
		return contentPane;
	}

	public void setContentPanel(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getTextFieldNumHab() {
		return textFieldNumHab;
	}

	public void setTextFieldNumHab(JTextField textFieldNumHab) {
		this.textFieldNumHab = textFieldNumHab;
	}

	public JComboBox getComboBoxPiso() {
		return comboBoxPiso;
	}

	public void setComboBoxPiso(JComboBox comboBoxPiso) {
		this.comboBoxPiso = comboBoxPiso;
	}

	public JComboBox getComboBoxTipoHab() {
		return comboBoxTipoHab;
	}

	public void setComboBoxTipoHab(JComboBox comboBoxTipoHab) {
		this.comboBoxTipoHab = comboBoxTipoHab;
	}

	public HashMap<String, TipoHabitacion> getMapeo() {
		return mapeo;
	}

	public void setMapeo(HashMap<String, TipoHabitacion> mapeo) {
		this.mapeo = mapeo;
	}

	public JPanel getPanelInfo() {
		return panelInfo;
	}

	public void setPanelInfo(JPanel panelInfo) {
		this.panelInfo = panelInfo;
	}

	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public void setPanelBotones(JPanel panelBotones) {
		this.panelBotones = panelBotones;
	}
	
}
