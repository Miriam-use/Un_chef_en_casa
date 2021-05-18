package api.ecomove.back.service;

import java.util.List;

import api.ecomove.back.entity.Pareja;

public interface ParejaService {

	public Pareja save(Pareja pareja); 
	
	public Pareja findByMatricula(String matricula);
	
	public Pareja findById(Long id);
	
	public void delete(Long id);
	
	public Pareja update(String matricula, String dispositivo);

	public List<Pareja> getListaDeParejas();

	public Pareja findByIdDispositivo(String disp);

	public List<Pareja> findByidentificadorDispositivo(String term);
}
