package api.ecomove.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.ecomove.back.entity.Receta;

@Repository
public interface RecetaDao extends JpaRepository<Receta, Long> {
	
	@Query("select p from Receta p where p.titulo like %?1%")
	public Receta findRecetaByTitulo(String term);
	
	@Query("select p from Receta p where p.titulo like %?1%")
	public List<Receta> findCocheByTitulo(String term);
	
	@Query("select p from Receta p where p.id like %?1%")
	public Receta findRecetaById(String term);
	
	@Query("select p from Receta p where p.id like %?1%")
	public List<Receta> findRecetaById(Long id);

}
