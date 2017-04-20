package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

	public class Tarea implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer id;
		private String descripcion;
		private String titulo;
		private Integer estado;
		private Integer creado_por;
		private Integer tipo_tarea;

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
			return tipo_tarea;
		}

		public void setTipoTarea(Integer tipo_Tarea) {
			this.tipo_tarea = tipo_Tarea;
		}	
	}
