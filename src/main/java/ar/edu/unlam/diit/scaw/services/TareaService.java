package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Tarea;

public interface TareaService {

	public void save(Tarea tarea);

	public List<Tarea> findAll();
}
