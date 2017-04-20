package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	public void save(Usuario usuario);

	public void deleteUsr(String usrName);
	
	public List<Usuario> findAll();
	
	//public List<Usuario> findUsr(String nombreUsr);
}
