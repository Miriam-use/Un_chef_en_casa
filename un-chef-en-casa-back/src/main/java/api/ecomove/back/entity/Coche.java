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
//TODO: Ponerle nombre que tiene en la base de datos
@Table(name="coches")
public class Coche implements Serializable {

	//TODO: Modificar los campos en funcion a como esten en la base de datos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="El campo matricula no puede estar vacio")
	@Column(nullable=false, unique=true)
	private String matricula;
	
	@NotEmpty(message ="El campo modelo no puede estar vacio")
	@Column(nullable=false)
	private String modelo;
	
	@NotEmpty(message ="El campo etiqueta no puede estar vacio")
	@Column(nullable=false)
	private String etiqueta;
	
	
	private boolean activo;
	
	private String  idispositivo;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public String getIdispositivo() {
		return idispositivo;
	}

	public void setIdispositivo(String idispositivo) {
		this.idispositivo = idispositivo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;
	
}
