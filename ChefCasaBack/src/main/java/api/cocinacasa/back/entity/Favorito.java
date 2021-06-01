package api.cocinacasa.back.entity;

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
@Table(name="favorito")
public class Favorito implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="El campo idusuario no puede estar vacio")
	@Column(nullable=false)
	private String idusuario;
	
	@NotEmpty(message ="El campo idreceta no puede estar vacio")
	@Column(nullable=false)
	private String idreceta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getIdreceta() {
		return idreceta;
	}

	public void setIdreceta(String idreceta) {
		this.idreceta = idreceta;
	}
	
}
