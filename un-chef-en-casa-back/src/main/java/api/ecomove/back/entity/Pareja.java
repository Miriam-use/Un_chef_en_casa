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
@Table(name = "parejas")
public class Pareja implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="El campo matricula no puede estar vacio")
	@Column(nullable=false, unique=true)
	private String matricula;
	
	@NotEmpty(message = "El campo id de dispositivo no puede estar vacio")
	@Column(nullable = false,unique = true)
	private String idDispositivo;
	
	//-------------------------------------------------Builder-------------------------------------------------------------
	
	public Pareja(@NotEmpty(message = "El campo matricula no puede estar vacio") String matricula,
			@NotEmpty(message = "El campo id de dispositivo no puede estar vacio") String idDispositivo) {
		super();
		this.matricula = matricula;
		this.idDispositivo = idDispositivo;
	}

	
	//--------------------------------------------------------------Getter&Setter----------------------------------------------
	
	public Pareja(Long id, @NotEmpty(message = "El campo matricula no puede estar vacio") String matricula,
			@NotEmpty(message = "El campo id de dispositivo no puede estar vacio") String idDispositivo) {
		
		this.id = id;
		this.matricula = matricula;
		this.idDispositivo = idDispositivo;
	}


	

	public Pareja() {
		super();
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

	public String getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	

}
