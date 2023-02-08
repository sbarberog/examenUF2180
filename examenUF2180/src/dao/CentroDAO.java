/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Centro;

/**
 * @author David
 * Clase que implementa un CRUD de la base batos
 * (Create, Read, update y delete)
 */
public class CentroDAO {

	private ConexionBD conexion;
	
    public CentroDAO() {
        this.conexion = new ConexionBD();
    }


    public ArrayList<Centro> obtenerCentros() {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		ArrayList<Centro> lista = new ArrayList<Centro>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from centros");
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(resultado.next()) {
				int cod_centro = resultado.getInt("cod_centro");
				String nombre = resultado.getString("nombre");
				String direccion = resultado.getString("direccion");
				
				Centro centro = new Centro(cod_centro, nombre,direccion);
				lista.add(centro);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre centros: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return lista;
    }

    
    public Centro obtenerCentro(int cod_centro) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Centro c=null;
		
		try {
			consulta = con.prepareStatement("select * from centros"
					+ " where cod_centro = ?");
			consulta.setInt(1, cod_centro);
			resultado=consulta.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if (resultado.next()) {
				String nombre = resultado.getString("nombre");
				String direccion = resultado.getString("direccion");
				
				c = new Centro(cod_centro, nombre,direccion);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre centros: "
		         +e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return c;
    }


    public int insertarCentro(Centro centro) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO Centros (cod_centro,nombre,direccion)"
					+ " VALUES (?,?,?) ");
			
			
			consulta.setInt(1, centro.getCod_centro() );
			consulta.setString(2, centro.getNombre());
			consulta.setString(3, centro.getDireccion());
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la inserción del centro: "
		        +e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }

    public int actualizarAutor(Centro centro) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("UPDATE centros \r\n"
					+ "SET nombre = ?, direccion = ?\r\n"
					+ "WHERE cod_centro = ?;");
			
			consulta.setString(1, centro.getNombre());
			consulta.setString(2, centro.getDireccion());
			consulta.setInt(3, centro.getCod_centro());
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion de centros: "
					+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }


    public int eliminarAutores(Centro centro) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("DELETE FROM centros\r\n"
					+ "WHERE cod_centro = ?");
			
			consulta.setInt(1, centro.getCod_centro());
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado de Centros: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }

}
