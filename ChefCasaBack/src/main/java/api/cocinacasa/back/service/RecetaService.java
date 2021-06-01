package api.cocinacasa.back.service;

import java.util.List;

import api.cocinacasa.back.entity.Receta;

public interface RecetaService {
	
	public List<Receta>findAll();
	
	public Receta save(Receta receta);
	
	public Receta findById(Long id);
	
	public void delete(Long id);
	
	public List<Receta> findCocheByTitulo(String term);
	
	public Receta findRecetaByTitulo(String term);
	
	public Receta findRecetaById(String term);
	
	public List<Receta>findRecetaById(Long id);

}
