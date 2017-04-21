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
	public void deleteUsr(String usrName) {		
		String sql = "DELETE FROM USUARIO WHERE USUARIO LIKE :usuario";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("usuario", usrName);
		jdbcTemplate.update(sql, params);		
	}

	@Override
	public void changeUsrState(String usrName, String state) {		
		String sql = "UPDATE USUARIO SET ESTADO = :estado WHERE USUARIO LIKE :usuario";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("estado", state);
		params.put("usuario", usrName);
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
