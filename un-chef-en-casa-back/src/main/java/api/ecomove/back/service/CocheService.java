package api.ecomove.back.service;

import java.util.List;


import api.ecomove.back.entity.Coche;

public interface CocheService {
	
	public List<Coche>findAll();
	
	public Coche save(Coche coche);
	
	public Coche findById(Long id);
	
	public void delete(Long id);
	
	public List<Coche> findCocheByMatricula(String term);
	
	public List<Coche> findCocheByModelo(String term);
	
	public List<Coche> findCocheByEtiqueta(String term);
	
	public Coche findCocheByMatriculas(String term);
}
