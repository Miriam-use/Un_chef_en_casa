package api.cocinacasa.back.service;

import java.util.List;

import api.cocinacasa.back.entity.Proveedor;
import api.cocinacasa.back.entity.Viaje;


public interface BlockchainService {
	
	public List<Viaje>findAll();
	
	public Viaje findById(Long id);
	
	public Viaje findByRideId(Long rideid);
	
	public Viaje save(Viaje usuario);
	
	public void delete(Long id);
	
	public List<Proveedor>findAllProveedores();
	
	public List<Viaje>getViajesPorUsuario(Long usuarioid);
}