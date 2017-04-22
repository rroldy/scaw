package ar.edu.unlam.diit.scaw.daos;

import java.util.List;


import ar.edu.unlam.diit.scaw.entities.Tarea;

public interface TareaDao {
	public void save(Tarea tarea);

	public List<Tarea> findAll();
	
	public void deleteTarea(Integer id);
	
	//public void editTarea(Tarea tarea);
}
