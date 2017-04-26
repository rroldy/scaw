package ar.edu.unlam.diit.scaw.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

@ManagedBean(name = "usuarioBean", eager = true)
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario = null;
	private String password = null;
	private Integer tipo = null;
	private String aprobado = null;
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	UsuarioService service = (UsuarioService) context.getBean("usuarioService");
	
	
	public UsuarioBean() {
		super();
	}
	
	public String save() {
		
		Usuario usuario = buildUsuario();
		
		service.save(usuario);
		
		return "usuarios";
	}
	
	public String update(String usrName, int tipo, String aprobado) {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String userOld = ec.getRequestParameterMap().get("formId:userOld");
		
		service.update(userOld, usrName, tipo, aprobado);
		
		return "usuarios";
	}
	
	public String deleteUsr(String nombreUsr) {
		
		service.deleteUsr(nombreUsr);				
		
		return "usuarios";
	}	
	
	public String editUsr(String nombreUsr) {
		List<Usuario> list = service.editUsr(nombreUsr);
		if(list.isEmpty()) {
			return "editarUsr";
		}
		
		this.setUsuario(nombreUsr);
		this.setAprobado(list.get(0).getAprobado());
		this.setTipo(list.get(0).getTipo());		
			
		return "editarUsrDisplay";
	}
	
	public String changeUsrState(int idUsr, String state) {
		
		if (state != "") {
			service.changeUsrState(idUsr, state);				
		}
		return "usuarios";
	}
	
	public List<Usuario> getFindAll() {
		List<Usuario> list = service.findAll();
		return list;
	}
		
	private Usuario buildUsuario() {
		Usuario usuario = new Usuario();
		usuario.setUsuario(this.usuario);
		usuario.setPassword(this.password);
		usuario.setTipo(this.tipo);
		usuario.setAprobado(this.aprobado);
		
		return usuario;
	}

	public UsuarioBean(String usuario, String password, Integer tipo, String aprobado) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.tipo = tipo;
		this.aprobado = aprobado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	// Generacion de la sesion de usuario 
	public String crearSesion(String usrName, String password ) throws ServletException { 
		
		List<Usuario> list = service.crearSesion(usrName, password);
		
		if(list.isEmpty()) {	// usuario no registrado
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "login";
		
		} else {				// usuario registrado en sistema
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			
			//Se crea una nueva sesión para este usuario
			Usuario usuario = new Usuario();
			usuario.setId(list.get(0).getId());
			usuario.setUsuario(list.get(0).getUsuario());
			usuario.setTipo(list.get(0).getTipo());
			usuario.setAprobado(list.get(0).getAprobado());
			
			HttpSession session = request.getSession(true);
			session.setAttribute("id", usuario.getId());
			session.setAttribute("usuario", usuario.getUsuario());
			session.setAttribute("tipo", usuario.getTipo());
			session.setAttribute("aprobado", usuario.getAprobado());
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			
			if (usuario.getTipo() == 1) { 
				return "usuarios";	// Administrador -> muestro usuarios					
			} else {
				return "tareas";	// Usuarios -> muestro tareas
			}
			
		}
	}
	
	public String eliminarSesion() throws ServletException{
				
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
	
		request.logout();
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
	
	//este metodo se debe incluir en las vistas para resstringir acceso no autorizado
	public void verificarSesion() throws IOException{
		//TODO: verificar el tipo usuario -> comun/adm -> para ver en que pagina intenta acceder
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) {

			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		}
	}

}
