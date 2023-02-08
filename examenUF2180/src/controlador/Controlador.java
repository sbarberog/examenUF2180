/**
 * 
 */
package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.CentroDAO;
import modelo.Centro;
import vista.DialogoAnadirCentro;
import vista.VentanaMostrarCentros;
import vista.VentanaPpal;

/**
 * @author David
 *
 */
public class Controlador {

	// Ventanas del sistema
	private VentanaPpal ventanaPpal;
	private VentanaMostrarCentros ventanaMostrarCentros;
	private DialogoAnadirCentro dialogoAnadirCentro;
	
	// Objetos DAO o CRUD de la base de datos
	private CentroDAO centroDAO;

	
	
	public Controlador() {
		// Creamos las ventanas de la aplicaci�n
		ventanaPpal = new VentanaPpal();
		ventanaMostrarCentros = new VentanaMostrarCentros();
		dialogoAnadirCentro = new DialogoAnadirCentro();
		
		// Dando acceso al controlador desde las vistas
		ventanaPpal.setControlador(this);
		ventanaMostrarCentros.setControlador(this);
		dialogoAnadirCentro.setControlador(this);

		
		// Creamos los objetos DAO
		centroDAO = new CentroDAO();
	}
	
	
	/**
	 * M�todo que arranca el programa principal
	 */
	public void inciarPrograma() {
		ventanaPpal.setVisible(true);
	}
	
	public void mostrarListarCentros() {
		ArrayList<Centro> lista = centroDAO.obtenerCentros();
		ventanaMostrarCentros.setListaCentros(lista);
		ventanaMostrarCentros.setVisible(true);
	}
	
	public void mostrarInsertarCentros() {
		dialogoAnadirCentro.setVisible(true);
	}


	/** 
	 * M�todo del controlador que a�ade un nuevo centro a la tabla de centros
	 * @param centro
	 */
	public void insertaCentro(Centro centro) {
		// Invocando a centroDAO
		int resultado = centroDAO.insertarCentro(centro);
		if (resultado ==0) {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Error. no se ha podido insertar.");
		} else {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Insercion del centro correcta");
			dialogoAnadirCentro.setVisible(false);
		}
	}
	
	
}
