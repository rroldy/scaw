package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String usuario;
	private String password;
	private Integer tipo;
	private String aprobado;

	public Usuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuarior) {
		this.usuario = usuarior;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	public String getAprobado() {
		return aprobado;
	}

	public void setAprobado(String aprobado) {
		this.aprobado = aprobado;
	}	
}
