package api.cocinacasa.back.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	public Usuario() {
	}	

	public Usuario(Long id,
			@NotEmpty(message = "no puede estar vacío") @Size(min = 4, max = 8, message = "rango de caracteres-> de 4 a 8") String dasatos,
			@NotEmpty(message = "no puede estar vacío") String correo,
			@NotEmpty(message = "es requerido") String contrasena, UsuarioDetalle usuariodetalle,
			Date createAt, Boolean enabled, List<Role> roles, String bcaddress) {
		super();
		this.id = id;
		this.dasatos = dasatos;
		this.correo = correo;
		this.contrasena = contrasena;
		this.usuariodetalle = usuariodetalle;
		this.createAt = createAt;
		this.enabled = enabled;
		this.roles = roles;
		this.bcaddress = bcaddress;
	}

/******************************************************************************************************************/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@JsonIgnore
	@Column(name="idtableusuarios")
	private Long id;
	
	@NotEmpty(message="no puede estar vacío")
	@Size(min=4,max=8, message="rango de caracteres-> de 4 a 8")
	@Column(nullable=false)
	private String dasatos;
	
//	@Email(message="necesita estar bien formado")
	@NotEmpty(message="no puede estar vacío")
	@Column(nullable=false)
	private String correo;
	
	@Column(nullable=false)	
	@NotEmpty(message="es requerido")
	private String contrasena;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="usuariodetalle",referencedColumnName="idtableusuariosdetalle")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private UsuarioDetalle usuariodetalle;
	
	//@NotNull(message="se requiere una fecha")
	@Column(name="create_at",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		this.createAt=new Date();
	}
	
	private Boolean enabled;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="usuarios_roles",joinColumns=@JoinColumn(name="usuario_id"),inverseJoinColumns=@JoinColumn(name="role_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id","role_id"})})
	private List<Role> roles;
	
	@Column
	private String bcaddress;
	
/******************************************************************************************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDasatos() {
		return dasatos;
	}

	public void setDasatos(String dasatos) {
		this.dasatos = dasatos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public UsuarioDetalle getUsuariodetalle() {
		return usuariodetalle;
	}

	public void setUsuariodetalle(UsuarioDetalle usuariodetalle) {
		this.usuariodetalle = usuariodetalle;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getBcaddress() {
		return bcaddress;
	}

	public void setBcaddress(String bcaddress) {
		this.bcaddress = bcaddress;
	}
}