package api.ecomove.back.service;

import java.util.List;

import api.ecomove.back.entity.Receta;

public interface RecetaService {
	
	public List<Receta>findAll();
	
	public Receta save(Receta receta);
	
	public Receta findById(Long id);
	
	public void delete(Long id);
	
	public List<Receta> findCocheByTitulo(String term);
	
	public Receta findRecetaByTitulo(String term);

}
