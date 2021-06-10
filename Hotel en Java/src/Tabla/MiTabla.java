package Tabla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ClasesDePersonas.Persona;
import Hotel.Habitacion;
import Hotel.Hotel;
import Hotel.TipoHabitacion;
import Ventanas.InformacionHabitacion;
import Ventanas.InformacionTipoHabitacion;

public class MiTabla{

	public static JTable tablaHabitacion;
	public static JTable tablaTH;
	public static JTable tablaListaPasajeros;
	public static JButton botonEliminarHabitacion, botonEliminarTH;
	public static JButton botonModificarHabitacion, botonModificarTH;
	public static JDialog jdTH, jdHab;
	
	static{
		accionBotonEliminarHabitacion();
		accionBotonModificarHabitacion();
		accionBotonEliminarTH();
		accionBotonModificarTH();
		crearTablaHabitaciones();
		crearTablaTiposHabitaciones();
	}
	
	public MiTabla(){
		
	}
	
	public static DefaultTableModel cargarInfoTablaHab(){
		
		Object columnaNombresHab[]={"Número Habitación", "Número Piso", "Tipo Habitación", "Precio", "Disponibilidad", "Modificar", "Eliminar"};
		DefaultTableModel modeloHab= new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};

		modeloHab.setColumnIdentifiers(columnaNombresHab);		//agrega los nombres como cabecera
		/*agrega todos los datos de la lista de habitaciones que posee el hotel
		 * mostrando como primer dato el numero de la habitacion, 
		 * luego el numero de piso en que se encuentra
		 * luego el tipo de habitacion que és, su precio por noche, 
		 * y su disponibilidad (retorna true o false).
		 */
		for(Habitacion aux : Hotel.listaHabitaciones){		
			
			JLabel disp= new JLabel("Disponible");
			disp.setHorizontalAlignment(JLabel.CENTER);
			disp.setForeground(Color.green);
			if(aux.getDisponibilidad() == false){
				disp.setText("Ocupado");
				disp.setForeground(Color.RED);
			}
			
			Object filaUnicaHab[]= {"Habitación " + aux.getNumeroHab(), "Piso " + aux.getNumPiso(), aux.getTipoHab().getNombre(), "$ " + aux.getTipoHab().getPrecio(), disp, botonModificarHabitacion, botonEliminarHabitacion};
			modeloHab.addRow(filaUnicaHab);
		}
		
		return modeloHab;
	}
	
	public static void crearTablaHabitaciones(){
		
		tablaHabitacion = new JTable();
		tablaHabitacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaHabitacion.setDefaultRenderer(Object.class, new MiRender());				//permito que se pueda ingresar cualquier componente a cualquier celda (como JButton, JLabel, JComboBox, etc)
		
		tablaHabitacion.addMouseListener(new MouseAdapter(){
			@Override 
			public void mouseClicked(MouseEvent e) {
		        int columna= tablaHabitacion.getColumnModel().getColumnIndexAtX(e.getX());		//obtengo la columna seleccionada
		        int fila= e.getY()/tablaHabitacion.getRowHeight(); 							//obtengo la fila seleccionada

				if (fila < tablaHabitacion.getRowCount() && fila >= 0 && columna < tablaHabitacion.getColumnCount() && columna >= 0) {
				  
					Object value = tablaHabitacion.getValueAt(fila, columna);	//obtengo el componente que se encuentra en esa celda
					if (value instanceof JButton) {
						((JButton)value).doClick();								//activa la accion del boton correspondiente
					}

		        }
			}
		});
		
		tablaHabitacion.setModel(cargarInfoTablaHab());
		tablaHabitacion.setRowHeight(20);
	}
	
	public static DefaultTableModel cargarInfoTablaTH(){
		
		Object columnaNombresTH[]= {"Nombre", "Límite de Personas", "Precio", "Modificar", "Eliminar"};	
		DefaultTableModel modeloTH= new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};

		modeloTH.setColumnIdentifiers(columnaNombresTH);		
		for(TipoHabitacion aux : Hotel.th){					
			Object filaUnicaTH[]= {aux.getNombre(), "Máximo " + aux.getLimite(), "$ " + aux.getPrecio(), botonModificarTH, botonEliminarTH};
			modeloTH.addRow(filaUnicaTH);
		}
		
		return modeloTH;
	}

	public static void crearTablaTiposHabitaciones(){
		
		tablaTH = new JTable();
		tablaTH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaTH.setDefaultRenderer(Object.class, new MiRender());
		
		tablaTH.addMouseListener(new MouseAdapter(){
			@Override 
			public void mouseClicked(MouseEvent e) {
		        int columna= tablaTH.getColumnModel().getColumnIndexAtX(e.getX());		//obtengo la columna seleccionada
		        int fila= e.getY()/tablaTH.getRowHeight(); 							//obtengo la fila seleccionada

				if (fila < tablaTH.getRowCount() && fila >= 0 && columna < tablaTH.getColumnCount() && columna >= 0) {
				  
					Object value = tablaTH.getValueAt(fila, columna);	//obtengo el componente que se encuentra en esa celda
					if (value instanceof JButton) {
						((JButton)value).doClick();								//activa la accion del boton correspondiente
					}
		        }
			}
		});
		
		tablaTH.setModel(cargarInfoTablaTH());
		tablaTH.setRowHeight(20);
	}
	
	public static DefaultTableModel cargarInfoTablaListaPasajeros(int pos){
		
		Object columnaNombresListaPasajeros[]= {"Nombre", "Apellido", "DNI", "Teléfono", "Nacionalidad", "Fecha Nacimiento"};	
		DefaultTableModel modeloListaPasajeros= new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};

		modeloListaPasajeros.setColumnIdentifiers(columnaNombresListaPasajeros);	
		ArrayList<Persona> lista= Hotel.listaHabitaciones.get(pos).getListaPasajeros();
		
		for(Persona aux : lista){					
			Object filaUnicaListaPasajeros[]= {aux.getNombre(), aux.getApellido(), aux.getDNI(), aux.getTelefono(), aux.getNacionalidad(), aux.getFechaEntera()};
			modeloListaPasajeros.addRow(filaUnicaListaPasajeros);
		}
		
		return modeloListaPasajeros;
	}

	public static void crearTablaListaPasajeros(int pos){
		
		tablaListaPasajeros = new JTable();
		tablaListaPasajeros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaListaPasajeros.setDefaultRenderer(Object.class, new MiRender());
		
		tablaListaPasajeros.setModel(cargarInfoTablaListaPasajeros(pos));
		tablaListaPasajeros.setRowHeight(20);
	}

	public static void accionBotonEliminarHabitacion(){
		botonEliminarHabitacion= new JButton("Eliminar");
		
		botonEliminarHabitacion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int posFila= tablaHabitacion.getSelectedRow();
				
				if(posFila != -1){
					DefaultTableModel model = (DefaultTableModel) tablaHabitacion.getModel();
					
					String aux= (String) model.getValueAt(posFila, 0);					//obtengo el primer elemento de la tabla
					int x= Integer.parseInt(aux.substring(11));							/*al ser el primer elemento de Numero de habitaciones, quito "Habitacion " y leo solo el entero	
																						 *el numero de habitacion es un entero por lo tanto que quitar el dato String agregado en la tabla
																						 */
					int pos= Hotel.getPosHabitacionEspecifica(x);						//obtengo la habitacion con ese numero
					
					if(Hotel.listaHabitaciones.get(pos).getDisponibilidad()){
						boolean resultado= Hotel.borrarUnaHabitacion(Hotel.listaHabitaciones.get(pos));	//borro la habitacion de la lista
						
						if(resultado == true){
							JOptionPane.showMessageDialog(null, "Habitación borrada correctamente.", null, JOptionPane.INFORMATION_MESSAGE);
							model.removeRow(posFila);											//borro la fila seleccionada

							Hotel.cargarJSON();
						}
						
						else{
							JOptionPane.showMessageDialog(null, "Error al borrar la Habitación.", null, JOptionPane.ERROR_MESSAGE);
						}
					}
					
					else{
						JOptionPane.showMessageDialog(null, "Primero elimine todas las personas que se encuentran dentro de la habitación", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	public static void accionBotonModificarHabitacion(){
		botonModificarHabitacion= new JButton("Modificar");
		
		botonModificarHabitacion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int posFila= tablaHabitacion.getSelectedRow();
				
				if(posFila != -1){
					jdHab= new JDialog();
					DefaultTableModel model= (DefaultTableModel) tablaHabitacion.getModel();
					
					String aux= (String) model.getValueAt(posFila, 0);					
					int x= Integer.parseInt(aux.substring(11));
					
					int pos= Hotel.getPosHabitacionEspecifica(x);
					InformacionHabitacion ih= new InformacionHabitacion(pos);
					
					jdHab.setTitle("Modificar Habitación");
					jdHab.setLocation(500, 250);
					jdHab.add(ih.getContentPane());
					
					jdHab.pack();
					jdHab.setVisible(true);		
				}
			}
		});
	}
	
	public static void accionBotonEliminarTH(){
		botonEliminarTH= new JButton("Eliminar");
		
		botonEliminarTH.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				int posFila= tablaTH.getSelectedRow();
				
				if(posFila != -1){
					DefaultTableModel model = (DefaultTableModel) tablaTH.getModel();
					
					String aux= (String) model.getValueAt(posFila, 0);					//obtengo el primer elemento de la tabla				
					int x= Hotel.getPosTipoHabEspecifica(aux);				    			//obtengo la habitacion con ese numero
					boolean resultado= Hotel.borrarUnTipoHabitacion(Hotel.th.get(x));					//borro la habitacion de la lista
					
					if(resultado == true){
						JOptionPane.showMessageDialog(null, "Tipo de Habitación borrada correctamente.", null, JOptionPane.INFORMATION_MESSAGE);
						model.removeRow(posFila);											//borro la fila seleccionada
						
						for(Habitacion auxHab : Hotel.listaHabitaciones){
							if(auxHab.getTipoHab().getNombre().equalsIgnoreCase(aux)){
								auxHab.setTipoHab(new TipoHabitacion());
							}
						}

						Hotel.cargarJSON();

						MiTabla.tablaTH.setModel(MiTabla.cargarInfoTablaTH());
						MiTabla.tablaHabitacion.setModel(MiTabla.cargarInfoTablaHab());
					}
					
					else{
						JOptionPane.showMessageDialog(null, "Error al borrar el Tipo de Habitación.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});
	}
	
	public static void accionBotonModificarTH(){
		botonModificarTH= new JButton("Modificar");
		
		botonModificarTH.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int posFila= tablaTH.getSelectedRow();
				if(posFila != -1){
					
					jdTH= new JDialog();
					DefaultTableModel model= (DefaultTableModel) tablaTH.getModel();
					
					String aux= (String) model.getValueAt(posFila, 0);
					int x= Hotel.getPosTipoHabEspecifica(aux);
					InformacionTipoHabitacion ith= new InformacionTipoHabitacion(x);
					
					jdTH.setTitle("Modificar Tipo de Habitación");
					jdTH.setLocation(500, 250);
					jdTH.add(ith.getContentPane());
					
					jdTH.pack();
					jdTH.setVisible(true);		
				}
			}
		});
	}
}
