package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Tarea;

public interface TareaService {

	public void save(Tarea tarea);
	
	public void deleteTarea(Integer id);
	
	public List<Tarea> editTarea(Integer id);

	public List<Tarea> findAll();
	
	public void update(String id, String titulo, String descripcion, Integer tipoTarea, Integer estado);

}
