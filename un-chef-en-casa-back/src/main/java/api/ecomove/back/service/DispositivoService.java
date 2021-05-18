package api.ecomove.back.service;



import java.util.List;

import api.ecomove.back.entity.Dispositivo;

public interface DispositivoService {

	public List<Dispositivo> findAll();
	public Dispositivo findBynombre(String nombre);
	public Dispositivo findByIdentificadorDisp(String id);
	public List<Dispositivo> findByidentificadorDispositivo(String id);
	public Dispositivo save(Dispositivo dispositivo);
	public Dispositivo findByIDDispositivo(String iddispositivo);
}
