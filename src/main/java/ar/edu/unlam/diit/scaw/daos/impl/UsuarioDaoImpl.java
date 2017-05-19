package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public UsuarioDaoImpl() {
		super();
	}

	@Override
	public void save(Usuario usuario) {

		String sql = "INSERT INTO USUARIO (USUARIO, PASSWORD, TIPO, APROBADO) VALUES (:usuario, :password, :tipo, :aprobado)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", usuario.getUsuario());
		params.put("password", usuario.getPassword());
		params.put("tipo", usuario.getTipo());
		params.put("aprobado", usuario.getAprobado());
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public void update(String usrNameOld, String usrName, int tipo, String aprobado) {

		String sql = "UPDATE USUARIO SET USUARIO = :usuario, TIPO = :tipo, APROBADO = :aprobado WHERE USUARIO LIKE :usuarioOld";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuarioOld", usrNameOld);
		params.put("usuario", usrName);
		params.put("tipo", tipo);
		params.put("aprobado", aprobado);
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public void updateInfoPersonal(String usrNameOld, String usrName, String password) {

		String sql = "UPDATE USUARIO SET USUARIO = :usuario, PASSWORD = :password WHERE USUARIO LIKE :usuarioOld";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuarioOld", usrNameOld);
		params.put("usuario", usrName);
		params.put("password", password);
		
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public void deleteUsr(String usrName) {		
		String sql = "DELETE FROM USUARIO WHERE USUARIO LIKE :usuario";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("usuario", usrName);
		jdbcTemplate.update(sql, params);		
	}
	
	@Override
	public void cerrarCuenta(String usrName) {
		String sql;
		
		/* BORRADO DE TAREAS CORRESPONDIENTES AL USUARIO */
		
		sql = "DELETE FROM TAREA WHERE CREADO_POR = (SELECT ID FROM USUARIO WHERE USUARIO LIKE :usuario)";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("usuario", usrName);
		jdbcTemplate.update(sql, params);		

		/* BORRADO DEL USUARIO */
				
		sql = "DELETE FROM USUARIO WHERE USUARIO LIKE :usuario";

		params = new HashMap<String, Object>();

		params.put("usuario", usrName);
		jdbcTemplate.update(sql, params);
	}
	
	@Override
	public List<Usuario> searchUsr(String usrName) {		
		String sql = "SELECT * FROM USUARIO WHERE USUARIO LIKE :usuario";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", usrName);
		
		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;	
	}
	
	@Override
	public void changeUsrState(int id, String estado) {		
		String sql = "UPDATE USUARIO SET APROBADO = :estado WHERE ID = :id";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("estado", estado);
		params.put("id", id);
		jdbcTemplate.update(sql, params);					
	}
	
	@Override
	public List<Usuario> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM USUARIO";

		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;
	}
				
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Usuario> crearSesion(String usrName, String password){
		String sql = "SELECT * FROM USUARIO WHERE USUARIO LIKE :usuario AND PASSWORD LIKE :password AND APROBADO LIKE :aprobado";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", usrName);
		params.put("password", password);
		params.put("aprobado", "S");
		
		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;
		
	}

	private static final class UsuarioMapper implements RowMapper<Usuario> {

		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setPassword(rs.getString("password"));
			usuario.setTipo(rs.getInt("tipo"));
			usuario.setAprobado(rs.getString("aprobado"));
			return usuario;
		}
	}
}
