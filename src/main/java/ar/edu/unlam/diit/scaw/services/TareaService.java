package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Tarea;

public interface TareaService {

	public void save(Tarea tarea);
	
	public void deleteTarea(Integer id);
	
	//public void editTarea(Tarea tarea);

	public List<Tarea> findAll();
}
