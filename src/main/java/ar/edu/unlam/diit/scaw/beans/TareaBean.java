package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.services.TareaService;


@ManagedBean(name = "tareaBean", eager = true)
@RequestScoped
public class TareaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
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
	public String deleteTarea(String id) {
		
		service.deleteTarea(Integer.parseInt(id));				
	
		return "tareas";
	}
	
	//@Named(value = "tareaBean")
	//@RequestScoped	
	public String editTarea(String id) {
		List<Tarea> list = service.editTarea(Integer.parseInt(id));

		if(list.isEmpty()) {
			return "editarTarea";
		}
		this.setId(list.get(0).getId());
		this.setTitulo(list.get(0).getTitulo());
		this.setDescripcion(list.get(0).getDescripcion());
		this.setEstado(list.get(0).getEstado());
		this.setTipoTarea(list.get(0).getTipoTarea());
				
		return "editarTareaDisplay";
	}
	
	public String editTarea1(String id, String titulo, String descripcion, String estado, String tipo) {		
		/*String id = (String)ae.getComponent().getAttributes().get("tarea");
		String titulo = (String)ae.getComponent().getAttributes().get("tarea.titulo");
		String descripcion = (String)ae.getComponent().getAttributes().get("tarea.descripcion");
		String estado = (String)ae.getComponent().getAttributes().get("tareaBean.estado");
		String tipo = (String)ae.getComponent().getAttributes().get("tarea.tipo");
		*/
		this.setId(Integer.parseInt(id));
		this.setTitulo(titulo);
		this.setDescripcion(descripcion);
		this.setEstado(Integer.parseInt(estado));
		this.setTipoTarea(Integer.parseInt(tipo));
		
		return "editarTareaDisplay";
	}

	public String update(String titulo, String descripcion, Integer tipoTarea, Integer estado) {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String idTarea = ec.getRequestParameterMap().get("formId:idOld");
		
		service.update(idTarea, titulo, descripcion, tipoTarea,estado);
		
		return "tareas";
	}
	public List<Tarea> getFindAll() {
		List<Tarea> list = service.findAll();
		return list;
	}
	
	private Tarea buildTarea() {
		Tarea tarea = new Tarea();
		tarea.setId(this.id);
		tarea.setTitulo(this.titulo);
		tarea.setDescripcion(this.descripcion);
		tarea.setEstado(this.estado);
		tarea.setCreadoPor(this.creado_por);
		tarea.setTipoTarea(this.tipoTarea);
		
		return tarea;
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
}
