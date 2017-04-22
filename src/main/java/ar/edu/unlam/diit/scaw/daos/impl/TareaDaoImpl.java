package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ar.edu.unlam.diit.scaw.daos.TareaDao;
import ar.edu.unlam.diit.scaw.entities.Tarea;

public class TareaDaoImpl implements TareaDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public TareaDaoImpl(){
		super();
	}
	
	@Override
	public void save(Tarea tarea) {

		String sql = "INSERT INTO TAREA (id,titulo, descripcion, estado, creado_por,tipoTarea) VALUES (:id, :titulo, :descripcion, :estado, :creado_por, :tipoTarea)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", tarea.getId());
		params.put("titulo", tarea.getTitulo());
		params.put("descripcion", tarea.getDescripcion());
		params.put("estado", tarea.getEstado());
		params.put("creado_por", tarea.getCreadoPor());
		params.put("tipoTarea", tarea.getTipoTarea());
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public void deleteTarea(Integer id) {		
		String sql = "DELETE FROM TAREA WHERE id = :idTarea";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("idTarea", id);
		jdbcTemplate.update(sql, params);		
	}
	
	@Override
	public List<Tarea> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();

		//String sql = "SELECT T.*, U.USUARIO FROM TAREA T INNER JOIN USUARIO U ON U.ID = T.CREADO_POR";
		String sql = "SELECT * FROM TAREA";
		
		List<Tarea> result = jdbcTemplate.query(sql, params, new TareaMapper());

		return result;
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final class TareaMapper implements RowMapper<Tarea> {

		public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tarea tarea = new Tarea();
			
			tarea.setId(rs.getInt("id"));
			tarea.setTitulo(rs.getString("titulo"));
			tarea.setDescripcion(rs.getString("descripcion"));
			tarea.setEstado(rs.getInt("estado"));
			tarea.setCreadoPor(rs.getInt("creado_por"));
			//tarea.setUsuarioCreador(rs.getString("usuario"));
			tarea.setTipoTarea(rs.getInt("tipoTarea"));
			return tarea;
		}
	}
}
