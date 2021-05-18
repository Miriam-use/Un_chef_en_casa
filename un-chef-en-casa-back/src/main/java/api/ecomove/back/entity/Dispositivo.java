package api.ecomove.back.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "dispositivos")
public class Dispositivo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Value("${aut}:ERROR")
	private String AUTH;

	private String nombre;

	private String identificadorDispositivo;

	private String longitud;

	private String latitud;

	private String velocidad;
	// token admin SharedAccessSignature
	// sr=a8b19977-9c37-4e01-bc86-f5990544bcb8&sig=gaSLKfM9MUo6uSd%2B83nDJNdejNW0aFoPmYFFsx9XVjY%3D&skn=admin&se=1650104456226

	public Long getId() {
		return id;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificadorDispositivo() {
		return identificadorDispositivo;
	}

	public void setIdentificadorDispositivo(String identificadorDispositivo) {
		this.identificadorDispositivo = identificadorDispositivo;
	}

	public void setAUTH(String auth) {
		this.AUTH = auth;
	}

	public String getAUTH() {
		return this.AUTH;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
