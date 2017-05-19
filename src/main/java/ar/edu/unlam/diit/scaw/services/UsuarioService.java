package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {

	public void save(Usuario usuario);

	public void deleteUsr(String nombreUsr);
	
	public void cerrarCuenta(String nombreUsr);
	
	public List<Usuario> findAll();

	public void changeUsrState(int idUsr, String state);

	public List<Usuario> searchUsr(String nombreUsr);

	public void update(String usrNameOld, String usrName, int tipo, String aprobado);

	public void updateInfoPersonal(String usrNameOld, String usrName, String password);
	
	public List<Usuario> crearSesion(String usrName, String password);

}
