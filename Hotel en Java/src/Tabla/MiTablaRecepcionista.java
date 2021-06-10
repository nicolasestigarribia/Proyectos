package Tabla;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Hotel.Habitacion;
import Hotel.Hotel;
import Hotel.TipoHabitacion;

public class MiTablaRecepcionista {
	
	public static JTable tablaHabitacion;
	public static JTable tablaTH;
	public static JTable tablaListaPasajeros;
	public static JButton CheckOut;
	public static JButton CheckIn;
	
	static{
		
		accionBotonCheckIn();
		accionBotonCheckOut();
		crearTablaHabitaciones();
		crearTablaTiposHabitaciones();
	}
	
	public MiTablaRecepcionista(){
		
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
			Object filaUnicaTH[]= {aux.getNombre(), "Máximo " + aux.getLimite(), "$ " + aux.getPrecio()};
			modeloTH.addRow(filaUnicaTH);
		}
		
		return modeloTH;
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
			
			Object filaUnicaHab[]= {"Habitación " + aux.getNumeroHab(), "Piso " + aux.getNumPiso(), aux.getTipoHab().getNombre(), "$ " + aux.getTipoHab().getPrecio(), disp, CheckIn, CheckOut};
			modeloHab.addRow(filaUnicaHab);
		}
		
		return modeloHab;
	}

	
}

