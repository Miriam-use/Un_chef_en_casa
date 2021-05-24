package api.ecomove.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.ecomove.back.entity.Favorito;

public interface FavoritoDao extends JpaRepository<Favorito, Long> {

	@Query("select u from Favorito u")
	public List<Favorito> findByAll();
}
