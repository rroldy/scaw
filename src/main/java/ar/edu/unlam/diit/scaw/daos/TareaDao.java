package ar.edu.unlam.diit.scaw.daos;

import java.util.List;


import ar.edu.unlam.diit.scaw.entities.Tarea;

public interface TareaDao {
	public void save(Tarea tarea);

	public List<Tarea> findAll();
	
	public void deleteTarea(Integer id);
	
	public List<Tarea> editTarea(Integer tarea);
	
	public void update(String id, String titulo, String descripcion, Integer tipoTarea, Integer estado);
}
