package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {

	public void save(Usuario usuario);

	public void deleteUsr(String nombreUsr);
	
	public List<Usuario> findAll();

	public void changeUsrState(int idUsr, String state);

	public List<Usuario> editUsr(String nombreUsr);

	public void update(String usrNameOld, String usrName, int tipo, String aprobado);

}
