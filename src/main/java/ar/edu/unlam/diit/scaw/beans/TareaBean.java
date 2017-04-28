package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.services.TareaService;


@ManagedBean(name = "tareaBean", eager = true)
@RequestScoped
public class TareaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String titulo = null;
	private String descripcion = null;
	private Integer tipoTarea = null;
	private Integer estado = null;
	private Integer creado_por = null;
	private String usuarioCreador = null;
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	TareaService service = (TareaService) context.getBean("tareaService");
	
	public TareaBean(){
		super();
	}
	
	public String save(String creadoPor) {
		
		Tarea tarea = buildTarea();
		tarea.setCreadoPor(Integer.parseInt(creadoPor));
		service.save(tarea);
		
		return "tareas";
	}
	public String deleteTarea(String id) {
		
		service.deleteTarea(Integer.parseInt(id));				
	
		return "tareas";
	}
		
	// Para la edicion de una tarea
	public String editTarea(String id, String titulo, String descripcion, String tipo, String estado, String usrCreador) {		
		this.setId(Integer.parseInt(id));
		this.setTitulo(titulo);
		this.setDescripcion(descripcion);		
		this.setTipoTarea(Integer.parseInt(tipo));
		this.setEstado(Integer.parseInt(estado));
		this.setUsuarioCreador(usrCreador);
		
		return "editarTareaDisplay";
	}
	
	// Visualizar tarea
	public String viewTarea(String id) {	
		List<Tarea> list = service.searchTarea(Integer.parseInt(id));

		if(list.isEmpty()) {
			return "tareas";
		}
		this.setId(list.get(0).getId());
		this.setTitulo(list.get(0).getTitulo());
		this.setDescripcion(list.get(0).getDescripcion());
		this.setTipoTarea(list.get(0).getTipoTarea());
		this.setEstado(list.get(0).getEstado());		
		this.setUsuarioCreador(list.get(0).getUsuarioCreador());
		
		return "verTarea";
	}
	
	// Actualizar tarea
	public String update(String titulo, String descripcion, Integer tipoTarea, Integer estado) {	
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String idTarea = ec.getRequestParameterMap().get("formId:idOld");
		
		service.update(idTarea, titulo, descripcion, tipoTarea, estado);
		
		return "tareas";
	}
	
	// Busca todas las tareas guardadas
	public List<Tarea> getFindAll() {		
		List<Tarea> list = service.findAll();
		return list;
	}
	
	// Busca solo las tareas publicas (para los usuarios no registrados)
	public List<Tarea> getFindPublic() {	
		List<Tarea> list = service.findPublic();
		return list;
	}
	
	private Tarea buildTarea() {
		Tarea tarea = new Tarea();
		tarea.setId(this.id);
		tarea.setTitulo(this.titulo);
		tarea.setDescripcion(this.descripcion);
		tarea.setTipoTarea(this.tipoTarea);
		tarea.setEstado(this.estado);
		tarea.setCreadoPor(this.creado_por);		
		tarea.setUsuarioCreador(this.usuarioCreador);
		
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

	public String getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(String usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}	
}
