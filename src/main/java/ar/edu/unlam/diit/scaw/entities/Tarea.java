package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

	public class Tarea implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer id;
		private String descripcion;
		private String titulo;
		private Integer estado;
		private Integer creado_por;
		private Integer tipoTarea;
		private String usuarioCreador;

		public Tarea() {
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
		public Integer getEstado() {
			return estado;
		}

		public void setEstado(Integer estado) {
			this.estado = estado;
		}
		
		public Integer getCreadoPor() {
			return creado_por;
		}

		public void setCreadoPor(Integer creadoPor) {
			this.creado_por = creadoPor;
		}
		public Integer getTipoTarea() {
			return tipoTarea;
		}

		public void setTipoTarea(Integer tipoTarea) {
			this.tipoTarea = tipoTarea;
		}
		
		public String getUsuarioCreador() {
			return usuarioCreador;
		}

		public void setUsuarioCreador(String usuarioCreador) {
			this.usuarioCreador = usuarioCreador;
		}	
	}
