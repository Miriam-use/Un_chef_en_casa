package api.ecomove.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "incidencias")
public class Incidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="El campo velocidad no puede estar vacio")
	@Column(nullable = false)
	private String speed;
	
	@NotEmpty(message ="El campo fecha no puede estar vacio")
	@Column(nullable = false)
	private String fecha;
	
	
	@NotEmpty(message ="El campo matricula no puede estar vacio")
	@Column(nullable = false)
	private String matricula;
	
	
	
	private String observacion;
	
	
	//-------------------------------------------------- METODOS GETTERS && SETTERS-----------------------------------------------//
	
	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	
	

}
