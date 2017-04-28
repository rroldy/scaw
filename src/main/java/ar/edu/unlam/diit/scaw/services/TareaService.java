package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Tarea;

public interface TareaService {

	public void save(Tarea tarea);
	
	public void deleteTarea(Integer id);
	
	public List<Tarea> searchTarea(Integer id);

	public List<Tarea> findAll();
	
	public List<Tarea> findPublic();
	
	public void update(String id, String titulo, String descripcion, Integer tipoTarea, Integer estado);
	
}
