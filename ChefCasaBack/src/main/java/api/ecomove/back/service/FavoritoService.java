package api.ecomove.back.service;

import java.util.List;

import api.ecomove.back.entity.Favorito;

public interface FavoritoService {

	public Favorito save(Favorito favorito);
	
	public List<Favorito>findAll();
	
	public void delete(Long id);
	
	public List<Favorito> findByIdReceta(String receta);
	
	public List<Favorito> findByIdUsuario(String usu);
	
}