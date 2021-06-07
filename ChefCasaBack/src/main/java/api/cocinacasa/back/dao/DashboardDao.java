package api.cocinacasa.back.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DashboardDao {
	
	@Query(value="select A.totalviajeseco, B.totalviajesnoeco, C.viajescero,D.viajeseco,E.viajesc,F.viajesb,G.viajessin, H.ecocoins, I.kmeco, J.kmnoeco from " + 
			"(select count(*) totalviajeseco from viajesuber " + 
			"where tipotransporte is not null " + 
			"and tipotransporte not like 'Sin Etiqueta' " +
			"and status like 'completed' " +
			"and usuario like ?1)A, " + 
			"(select count(*) totalviajesnoeco from viajesuber " + 
			"where tipotransporte is not null " + 
			"and tipotransporte like 'Sin Etiqueta' " + 
			"and status like 'completed' " +
			"and usuario like ?1)B, " + 
			"(select count(*) viajescero from viajesuber " + 
			"where tipotransporte is not null " + 
			"and tipotransporte like 'Etiqueta Cero' " + 
			"and status like 'completed' " +
			"and usuario like ?1)C, " + 
			"(select count(*) viajeseco from viajesuber " + 
			"where tipotransporte is not null " + 
			"and tipotransporte like 'Etiqueta Eco' " + 
			"and status like 'completed' " +
			"and usuario like ?1)D, " + 
			"(select count(*) viajesc from viajesuber " + 
			"where tipotransporte is not null " + 
			"and tipotransporte like 'Etiqueta C' " + 
			"and status like 'completed' " +
			"and usuario like ?1)E, " + 
			"(select count(*) viajesb from viajesuber " + 
			"where tipotransporte is not null " + 
			"and tipotransporte like 'Etiqueta B' " + 
			"and status like 'completed' " +
			"and usuario like ?1)F, " + 
			"(select count(*) viajessin from viajesuber " + 
			"where tipotransporte is not null " + 
			"and tipotransporte like 'Sin Etiqueta' " + 
			"and status like 'completed' " +
			"and usuario like ?1)G, " + 
			"(select sum(ecocoins)ecocoins from viajesuber " + 
			"where usuario like ?1)H, " +
			"(select sum(distancia) kmeco from viajesuber " + 
			"where usuario like ?1 " + 
			"and tipotransporte not like 'Sin Etiqueta')I, " +
			"(select sum(distancia) kmnoeco from viajesuber " + 
			"where usuario like ?1 " + 
			"and tipotransporte like 'Sin Etiqueta')J",nativeQuery = true)
	public Map<String,String> findDatosDashboard(Long idusuario);
	

}