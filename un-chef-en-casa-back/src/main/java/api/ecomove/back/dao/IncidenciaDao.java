package api.ecomove.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.ecomove.back.entity.Incidencia;


@Repository
public interface IncidenciaDao extends JpaRepository<Incidencia, Long>{

	
	@Query("select u from Incidencia u where u.matricula = ?1 ")
	public Incidencia findByMatricula(String matricula);
	
	@Query("select u from Pareja u")
	public List<Incidencia> findByAll();

	@Query("select u from Incidencia u where u.matricula = ?1 ")
	public List<Incidencia> findByMatriculas(String term);
}