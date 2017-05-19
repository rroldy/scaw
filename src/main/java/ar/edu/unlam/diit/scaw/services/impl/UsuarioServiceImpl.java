package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public void update(String usrNameOld, String usrName, int tipo, String aprobado) {
		usuarioDao.update(usrNameOld, usrName, tipo, aprobado);
	}
	
	@Override
	public void updateInfoPersonal(String usrNameOld, String usrName, String password) {
		usuarioDao.updateInfoPersonal(usrNameOld, usrName, password);
	}
	
	//@Override
	public void deleteUsr(String usrName) {
		usuarioDao.deleteUsr(usrName);
	}
	
	//@Override
	public void cerrarCuenta(String usrName) {
		usuarioDao.cerrarCuenta(usrName);
	}
	
	//@Override
	public List<Usuario> searchUsr(String usrName) {
		return usuarioDao.searchUsr(usrName);
	}
	
	//@Override
	public void changeUsrState(int idUsr, String state) {
		usuarioDao.changeUsrState(idUsr, state);
	}
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}	

	public List<Usuario> crearSesion(String usrName, String password) {
		return usuarioDao.crearSesion(usrName, password);
	}
}
