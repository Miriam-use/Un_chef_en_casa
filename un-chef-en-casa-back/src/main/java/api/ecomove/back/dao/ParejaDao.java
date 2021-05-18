package api.ecomove.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.ecomove.back.entity.Pareja;

@Repository
public interface ParejaDao extends JpaRepository<Pareja, Long> {

	@Query("select u from Pareja u where u.matricula = ?1 ")
	public Pareja findByMatricula(String matricula);

	@Query("select u from Pareja u where u.idDispositivo = ?1 ")
	public Pareja findByIdDispositivo(String disp);

	@Query("select u from Pareja u where u.matricula = ?1 ")
	public List<Pareja> findByidentificadorDispositivo(String term);
	
	@Query("select u from Pareja u")
	public List<Pareja> findByAll();
}
