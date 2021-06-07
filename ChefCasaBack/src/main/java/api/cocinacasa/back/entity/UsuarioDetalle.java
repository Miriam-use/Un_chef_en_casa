package api.cocinacasa.back.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="usuariosdetalle")
public class UsuarioDetalle implements Serializable{

	private static final long serialVersionUID = 1L;

	public UsuarioDetalle() {
		
	}
		
	public UsuarioDetalle(Long id, String nombre, String apellidos, String pais, String localidad, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.pais = pais;
		this.localidad = localidad;
		this.rol = rol;
	}
/*
	public UsuarioDetalle(Long id, Usuario usuario, String nombre, String apellidos, String direccion, String oficina,
			String rol) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.oficina = oficina;
		this.rol = rol;
	}
*/
	
/******************************************************************************************************************/

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@JsonIgnore
	@Column(name="idtableusuariosdetalle")
	private Long id;
	
	@OneToOne(mappedBy="usuariodetalle",fetch=FetchType.LAZY)
	@JoinColumn(name="usuario",referencedColumnName="idtableusuarios")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JsonIgnore
	private Usuario usuario;
	
	private String nombre;
	
	private String apellidos;
	
	private String pais;
	
	private String localidad;
	
	private String rol;
	

	
/******************************************************************************************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

/*	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
*/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}