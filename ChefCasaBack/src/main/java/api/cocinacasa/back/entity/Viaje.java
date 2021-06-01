package api.cocinacasa.back.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="viajesuber")
public class Viaje implements Serializable{

	private static final long serialVersionUID = 1L;

	public Viaje() {
		
	}
		
	public Viaje(Long id, Long rideid, String requestid,
			@NotNull(message = "está vacío: hay que elegir una compañía") Proveedor proveedor,
			@NotNull(message = "se requiere una fecha") Date fecha, Long ecocoins, Usuario usuario, String origen,
			String destino, String idreceipt, float precio, String status, Date fechaida, String descripcion,
			String motivo, String tipotransporte, double consumo, double distancia, String txdataviaje,
			String txdataecocoins, String huellaemision, String huellasalvada) {
		super();
		this.id = id;
		this.rideid = rideid;
		this.requestid = requestid;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.ecocoins = ecocoins;
		this.usuario = usuario;
		this.origen = origen;
		this.destino = destino;
		this.idreceipt = idreceipt;
		this.precio = precio;
		this.status = status;
		this.fechaida = fechaida;
		this.descripcion = descripcion;
		this.motivo = motivo;
		this.tipotransporte = tipotransporte;
		this.consumo = consumo;
		this.distancia = distancia;
		this.txdataviaje = txdataviaje;
		this.txdataecocoins = txdataecocoins;
		this.huellaemision = huellaemision;
		this.huellasalvada = huellasalvada;
	}

/******************************************************************************************************************/

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@JsonIgnore
	@Column(name="idtableviajesuber")
	private Long id;
	
	private Long rideid;
	
	private String requestid;
	
	@NotNull(message="está vacío: hay que elegir una compañía")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proveedor",referencedColumnName="idtableproveedorviajes") 
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Proveedor proveedor;
	
	@NotNull(message="se requiere una fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@PrePersist
	public void prePersist() {
		this.fecha=new Date();
	}
	
	private Long ecocoins;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario",referencedColumnName="idtableusuarios")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario usuario;
	
	private String origen;
	
	private String destino;
	
	private String idreceipt;
	
	private float precio;
	
	private String status;
	
	@Temporal(TemporalType.DATE)
	private Date fechaida;
	
	private String descripcion;
	
	private String motivo;
	
	private String tipotransporte;
	
	private double consumo;
	
	private double distancia;
	
	private String txdataviaje;
	
	private String txdataecocoins;
	
	private String huellaemision;
	
	private String huellasalvada;

/******************************************************************************************************************/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRideid() {
		return rideid;
	}

	public void setRideid(Long rideid) {
		this.rideid = rideid;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getEcocoins() {
		return ecocoins;
	}

	public void setEcocoins(Long ecocoins) {
		this.ecocoins = ecocoins;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getIdreceipt() {
		return idreceipt;
	}

	public void setIdreceipt(String idreceipt) {
		this.idreceipt = idreceipt;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFechaida() {
		return fechaida;
	}

	public void setFechaida(Date fechaida) {
		this.fechaida = fechaida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getTipotransporte() {
		return tipotransporte;
	}

	public void setTipotransporte(String tipotransporte) {
		this.tipotransporte = tipotransporte;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getTxdataviaje() {
		return txdataviaje;
	}

	public void setTxdataviaje(String txdataviaje) {
		this.txdataviaje = txdataviaje;
	}

	public String getTxdataecocoins() {
		return txdataecocoins;
	}

	public void setTxdataecocoins(String txdataecocoins) {
		this.txdataecocoins = txdataecocoins;
	}

	public String getHuellaemision() {
		return huellaemision;
	}

	public void setHuellaemision(String huellaemision) {
		this.huellaemision = huellaemision;
	}

	public String getHuellasalvada() {
		return huellasalvada;
	}

	public void setHuellasalvada(String huellasalvada) {
		this.huellasalvada = huellasalvada;
	}	
}