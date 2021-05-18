package api.ecomove.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import api.ecomove.back.entity.Coche;

@Repository
public interface CocheDao extends JpaRepository<Coche, Long> {

	
	@Query("select p from Coche p where p.matricula like %?1%")
	public List<Coche> findCocheByMatricula(String term);
	
	
	@Query("select p from Coche p where p.modelo like %?1%")
	public List<Coche> findCocheByModelo(String term);
	
	
	
	@Query("select p from Coche p where p.etiqueta like %?1%")
	public List<Coche> findCocheByEtiqueta(String term);
	
	@Query("select p from Coche p where p.matricula like %?1%")
	public Coche findCocheByMatriculas(String term);


}
