package api.ecomove.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.ecomove.back.entity.Dispositivo;

@Repository
public interface DispositivoDao  extends JpaRepository<Dispositivo, Long>{

	
	public Dispositivo findBynombre(String nombre);
	
	@Query("select p from Dispositivo p where p.identificadorDispositivo like %?1%")
	public List<Dispositivo> findByidentificadorDispositivo(String iddisp);

	@Query("select p from Dispositivo p where p.identificadorDispositivo like %?1%")
	public Dispositivo findByIdentificadorDisp(String id);

	@Query("select p from Dispositivo p where p.identificadorDispositivo like %?1%")
	public Dispositivo findByIDDispositivo(String iddispositivo);
	
}
	
	
