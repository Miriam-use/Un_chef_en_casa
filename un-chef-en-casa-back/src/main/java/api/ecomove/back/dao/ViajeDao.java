package api.ecomove.back.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import api.ecomove.back.entity.Proveedor;
import api.ecomove.back.entity.Viaje;

@Repository
public interface ViajeDao extends JpaRepository<Viaje, Long>{
	
	@Query("from Proveedor")
	public List<Proveedor>findAllProveedores();

	@Query("select v from Viaje v where v.rideid =:rideid order by v.id desc")
	public Viaje findByRideId(@Param("rideid")Long rideid);
	
	@Query("select v from Viaje v where v.usuario.id =:usuarioid order by v.id desc")
	public List<Viaje>findViajesPorUsuario(@Param("usuarioid")Long usuarioid);
	
	@Query(value="select * from viajesuber where usuario like ?1 "
			+ "and status like ?2 "
			+ "and fechaida like ?3 "
			+ "and proveedor like ?4 "
			+ "and origen like ?5 "
			+ "and destino like ?6 "
			+ "order by idtableviajesuber desc",nativeQuery = true)
	public List<Viaje>findViajesFiltrados(String idusuario, String status, String fechaida, String proveedor, String origen, String destino);
	
	@Query(value="select * from viajesuber where usuario =:usuarioid " //select * from viajesuber where usuario like :usuarioid
			+ "and status like :status "
			+ "and fechaida like:fechaida "
			+ "and proveedor like :proveedor "
			+ "and origen like:origen "
			+ "and destino like :destino "
			+ "order by idtableviajesuber desc", 
			//countQuery ="select count(*) from viajesuber",
			nativeQuery = true)
	public Page<Viaje> findMisViajesFiltradosPaginados(@Param("usuarioid")String usuarioid, 
			@Param("status")String status, @Param("fechaida")String fechaida,
			@Param("proveedor")String proveedor, @Param("origen")String origen,
			@Param("destino")String destino, Pageable pageable);
	
	@Query("select v from Viaje v where v.usuario.id =:usuarioid order by v.id desc")
	public Page<Viaje> findMisViajesPaginado(@Param("usuarioid")Long usuarioid, Pageable pageable);	

}