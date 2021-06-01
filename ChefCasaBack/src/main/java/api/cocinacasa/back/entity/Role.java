package api.cocinacasa.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;


	public Role() {
	}
	
	public Role(Long id, String nombre, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuarios = usuarios;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@JsonIgnore
	@Column(name="idtableroles")
	private Long id;	

	@Column(unique=true,length=20)
	private String nombre;
	
	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private List<Usuario> usuarios;

/******************************************************************************************************************/
	
	public Long getId() {
		return id;
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}