package api.ecomove.back.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="proveedorviajes")
public class Proveedor implements Serializable{

	private static final long serialVersionUID = 1L;

	public Proveedor() {

	}

	public Proveedor(Long id, String nombre, String foto, boolean activo, String link, List<Viaje> viaje) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.foto = foto;
		this.activo = activo;
		this.link = link;
		this.viaje = viaje;
	}

/******************************************************************************************************************/

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtableproveedorviajes")
	private Long id;
	
	private String nombre;
	
	private String foto;
	
	private boolean activo;
	
	private String link;
	
	@OneToMany(mappedBy="proveedor",fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private List<Viaje>viaje;
	
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Viaje> getViaje() {
		return viaje;
	}

	public void setViaje(List<Viaje> viaje) {
		this.viaje = viaje;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	}