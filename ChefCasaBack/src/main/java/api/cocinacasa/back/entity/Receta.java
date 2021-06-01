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
@Table(name="receta")
public class Receta implements Serializable {
	
	//TODO: Modificar los campos en funcion a como esten en la base de datos
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@NotEmpty(message ="El campo titulo no puede estar vacio")
		@Column(nullable=false)
		private String titulo;
		
		@NotEmpty(message ="El campo detalle no puede estar vacio")
		@Column(nullable=false)
		private String detalle;
		
		@NotEmpty(message ="El campo tiempo no puede estar vacio")
		@Column(nullable=false)
		private String tiempo;
		
		@NotEmpty(message ="El campo mesa no puede estar vacio")
		@Column(nullable=false, unique=true)
		private String mesas;
		
		@NotEmpty(message ="El campo lista no puede estar vacio")
		@Column(nullable=false)
		private String lista;
		
		@NotEmpty(message ="El campo paso no puede estar vacio")
		@Column(nullable=false)
		private String pasos;
		
		@NotEmpty(message ="El campo idusuario no puede estar vacio")
		@Column(nullable=false)
		private String idusuario;
		
		private int  favorito;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getDetalle() {
			return detalle;
		}

		public void setDetalle(String detalle) {
			this.detalle = detalle;
		}

		public String getTiempo() {
			return tiempo;
		}

		public void setTiempo(String tiempo) {
			this.tiempo = tiempo;
		}

		public String getMesas() {
			return mesas;
		}

		public void setMesas(String mesas) {
			this.mesas = mesas;
		}

		public String getLista() {
			return lista;
		}

		public void setLista(String lista) {
			this.lista = lista;
		}

		public String getPasos() {
			return pasos;
		}

		public void setPasos(String pasos) {
			this.pasos = pasos;
		}

		public String getIdusuario() {
			return idusuario;
		}

		public void setIdusuario(String idusuario) {
			this.idusuario = idusuario;
		}

		public int getFavorito() {
			return favorito;
		}

		public void setFavorito(int favorito) {
			this.favorito = favorito;
		}
		

}
