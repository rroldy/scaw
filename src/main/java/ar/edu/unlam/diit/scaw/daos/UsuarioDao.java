package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	public void save(Usuario usuario);

	public void deleteUsr(String usrName);
	
	public void cerrarCuenta(String usrName);
	
	public List<Usuario> findAll();

	public void changeUsrState(int idUsr, String state);
	
	//corresponde al login de usuario
	//public Usuario buscarUsuario(String usuario, String password);

	public List<Usuario> searchUsr(String usrName);

	public void update(String usrNameOld, String usrName, int tipo, String aprobado);

	public void updateInfoPersonal(String usrNameOld, String usrName, String password);
	
	public List<Usuario> crearSesion(String usrName, String password);
	
	//public List<Usuario> findUsr(String nombreUsr);
}
