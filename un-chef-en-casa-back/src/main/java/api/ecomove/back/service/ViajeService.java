package api.ecomove.back.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import api.ecomove.back.entity.Proveedor;
import api.ecomove.back.entity.Viaje;


public interface ViajeService {
	
	public List<Viaje>findAll();
	
	public Viaje findById(Long id);
	
	public Viaje findByRideId(Long rideid);
	
	public Viaje save(Viaje usuario);
	
	public void delete(Long id);
	
	public List<Proveedor>findAllProveedores();
	
	public List<Viaje>getViajesPorUsuario(Long usuarioid);
	
	public List<Viaje>getViajesFiltrados(String idusuario, String status, String fechaida, String proveedor, String origen, String destino);
	
	public Page<Viaje>getMisViajesFiltradosPaginados( String usuarioid, String status, String fechaida, String proveedor, String origen, String destino, Pageable pageable);
	
	// pruebas ****************
	public Page<Viaje>findMisViajesPaginados(Long usuarioid, Pageable pageable);
	
	public Page<Viaje>findMisViajesPaginados2( int pageNumber, int numberOfElementsPerPage);
	// ************************
	
	

	
	
}