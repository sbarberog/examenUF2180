/**
 * 
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.CentroDAO;
import dao.DepartamentoDAO;
import modelo.Centro;
import modelo.Departamento;
import vista.DialogoAnadirCentro;
import vista.DialogoAnadirDepartamento;
import vista.VentanaMostrarCentros;
import vista.VentanaPpal;
import vista.VentanaMostrarDepartamentos;

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
	private DepartamentoDAO departamentoDAO;
	private VentanaMostrarDepartamentos ventanaMostrarDepartamentos;
	private DialogoAnadirDepartamento dialogoAnadirDepartamento;

	
	
	public Controlador() {
		// Creamos las ventanas de la aplicaci�n
		ventanaPpal = new VentanaPpal();
		ventanaMostrarCentros = new VentanaMostrarCentros();
		dialogoAnadirCentro = new DialogoAnadirCentro();
		ventanaMostrarDepartamentos = new VentanaMostrarDepartamentos();
		dialogoAnadirDepartamento= new DialogoAnadirDepartamento();
		
		// Dando acceso al controlador desde las vistas
		ventanaPpal.setControlador(this);
		ventanaMostrarCentros.setControlador(this);
		dialogoAnadirCentro.setControlador(this);
		ventanaMostrarDepartamentos.setControlador(this);
		dialogoAnadirDepartamento.setControlador(this);

		
		// Creamos los objetos DAO
		centroDAO = new CentroDAO();
		departamentoDAO=new DepartamentoDAO();
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
	
	public void mostrarListaDepartamentos() {
		ArrayList<Departamento> lista= departamentoDAO.obtenerDepartamentos();
		ventanaMostrarDepartamentos.setListaDepartamentos(lista);
		ventanaMostrarDepartamentos.setVisible(true);
	}


	public void insertaDepartamento(Departamento d) throws SQLException {
		
//		try {
			
			departamentoDAO.insertarDepartamento(d);
//			
//			JOptionPane.showMessageDialog(dialogoAnadirDepartamento, "Insercion del departamento correcta");
//			dialogoAnadirDepartamento.setVisible(false);
//	
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(dialogoAnadirDepartamento, "No se ha podido insertar el departamento", "Error", JOptionPane.ERROR_MESSAGE);
//		}
		
		
	}


	public void mostrarInsertarDepartamentos() {
		
		ArrayList<Centro> lista = centroDAO.obtenerCentros();
		dialogoAnadirDepartamento.setListaCentros(lista);
		dialogoAnadirDepartamento.setVisible(true);
	}
}
