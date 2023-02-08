package modelo;

import java.util.Objects;

/** 
 * Clase que modela un objeto de tipo Centro para trabajar con las tablas de la 
 * base de datos
 * @author David
 *
 */
public class Centro {

	private int cod_centro;
	private String nombre;
	private String direccion;
	
	
	/**
	 * Constructor sin parámetros. Sólo incializa las variables de tipo String
	 * a la cadena vacía
	 */
	public Centro() {
		this.nombre="";
		this.direccion="";
	}
	
	/**
	 * Constructor parametrizado para crear un objeto de la clase
	 * @param cod_centro int con el codigo del centro 
	 * @param nombre String el nombre del centro de trabajo
	 * @param direccion String el domicilio donde se encuentra el centro
	 */
	public Centro(int cod_centro, String nombre, String direccion) {
		this.cod_centro = cod_centro;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	/**
	 * Método que obtiene el el código del centro
	 * @return int con el código del centro
	 */
	public int getCod_centro() {
		return cod_centro;
	}

	/**
	 * Método que establece el valor para el código del centro
	 * @param cod_centro int con el código del centro
	 */
	public void setCod_centro(int cod_centro) {
		this.cod_centro = cod_centro;
	}

	/**
	 * Método para obtener el String que almacena el nombre del centro
	 * @return String con el nombre del centro
	 */
	public String getNombre() {
		return nombre;
	}

	/** 
	 * Método que establece el nombre del centro
	 * @param nombre String con el nombre a establecer
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** 
	 * Método que devuelve la dirección del centro
	 * @return String con la dirección del centro
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Método que devuelve la dirección del centro 
	 * @param String direccion La dirección del centro a devolver
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod_centro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Centro other = (Centro) obj;
		return cod_centro == other.cod_centro;
	}

	@Override
	public String toString() {
		return "Centro [cod_centro=" + cod_centro + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
	
}
