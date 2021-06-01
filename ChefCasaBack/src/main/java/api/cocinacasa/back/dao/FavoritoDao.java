package api.cocinacasa.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.cocinacasa.back.entity.Favorito;

public interface FavoritoDao extends JpaRepository<Favorito, Long> {

	@Query("select u from Favorito u")
	public List<Favorito> findByAll();
	
	@Query("select p from Favorito p where p.idreceta like %?1%")
	public List<Favorito> findByIdReceta(String receta);
	
	@Query("select p from Favorito p where p.idusuario like %?1%")
	public List<Favorito> findByIdUsuario(String usu);
}
