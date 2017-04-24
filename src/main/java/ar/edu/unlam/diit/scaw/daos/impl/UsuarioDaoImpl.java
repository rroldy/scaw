package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
	public void deleteUsr(String usrName) {		
		String sql = "DELETE FROM USUARIO WHERE USUARIO LIKE :usuario";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("usuario", usrName);
		jdbcTemplate.update(sql, params);		
	}

	@Override
	public List<Usuario> editUsr(String usrName) {		
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
	public Usuario buscarUsuario(String usuario, String password){
		
		
		
		
		Map<String, Object> params = new HashMap<String, Object>(); //contiene los datos que se usaran en la query
		Usuario usuarioEncontrado = new Usuario();
		
		//seteo de parametros
		params.put("usuario", usuario);
		params.put("password", password);

		String sql = "SELECT * FROM USUARIO WHERE usuario LIKE :usuario AND password LIKE :password";
		
		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());
		
		
		/*
		if(result.size() == 0){
			
			System.out.println("no se encontro el usuario");
			return null;
		}
		else{
			 usuarioEncontrado = result.get(0);
		
			return usuarioEncontrado; //obtuve datos de una consulta y los guardo en un array, indexo en posicion 0 porque solo obtengo uno y lo retorno
		}
		*/
		return null;
		
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
