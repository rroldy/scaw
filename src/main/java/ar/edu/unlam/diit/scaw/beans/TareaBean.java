package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.services.TareaService;


@ManagedBean(name = "tareaBean", eager = true)
@RequestScoped
public class TareaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descripcion = null;
	private String titulo = null;
	private Integer estado = null;
	private Integer creado_por = null;
	private Integer tipoTarea = null;
	//private Integer usuarioCreador = null;
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	TareaService service = (TareaService) context.getBean("tareaService");
	
	public TareaBean(){
		super();
	}
	
public String save() {
		
		Tarea tarea = buildTarea();
		
		service.save(tarea);
		
		return "tareas";
	}
	
	
	public List<Tarea> getFindAll() {
		List<Tarea> list = service.findAll();
		return list;
	}
	
	private Tarea buildTarea() {
		Tarea tarea = new Tarea();
		tarea.setTitulo(this.titulo);
		tarea.setDescripcion(this.descripcion);
		tarea.setEstado(this.estado);
		tarea.setCreadoPor(this.creado_por);
		tarea.setTipoTarea(this.tipoTarea);
		
		return tarea;
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
}
