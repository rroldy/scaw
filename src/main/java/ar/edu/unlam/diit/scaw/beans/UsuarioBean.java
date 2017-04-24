package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
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
	
	//generacion de la sesion de usuario 
	public String crearSesion(){ //este metodo DEBE RETORNAR SIEMPRE un String
		
		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
		
		String resultado;
		
		Usuario usuarioEncontrado = usuarioDaoImpl.buscarUsuario(usuario,password); //se busca el usuario
		
		if(usuarioEncontrado !=null){
			
			//se crea una nueva sesión para este usuario
			/*	
			HttpSession session = request.getSession(true);
			
			session.setAttribute("usuario", usuarioEncontrado.getUsuario());
			session.setAttribute("tipo", usuarioEncontrado.getTipo());
			session.setAttribute("id", usuarioEncontrado.getId());
			session.setAttribute("aprobado", usuarioEncontrado.getAprobado());
			*/
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioEncontrado);
			
			resultado = "welcome";
			
		}
		else
			resultado = "login";
		
		System.out.println(resultado);
		return resultado;
	}
	
	public String eliminarSesion(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "login";
	}
	
	//este metodo se debe incluir en las vistas para resstringir acceso no autorizado
	public boolean verificarSesion(){
		
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != null)
			return true;
		else return false;
	}

}
