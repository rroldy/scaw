package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.services.TareaService;
import ar.edu.unlam.diit.scaw.daos.TareaDao;
import ar.edu.unlam.diit.scaw.entities.Tarea;



public class TareaServiceImpl implements TareaService {
	@Autowired
	TareaDao tareaDao;
	
	@Override
	public void save(Tarea tarea) {
		tareaDao.save(tarea);
	}

	@Override
	public List<Tarea> findAll() {
		return tareaDao.findAll();
	}

	public TareaDao getTareaDao() {
		return tareaDao;
	}

	public void setTareaDao(TareaDao tareaDao) {
		this.tareaDao = tareaDao;
	}
	
	//@Override
	public void deleteTarea(Integer id) {
		tareaDao.deleteTarea(id);
	}
	
}
