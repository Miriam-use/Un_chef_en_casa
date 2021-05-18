package api.ecomove.back.service;

import java.util.List;

import api.ecomove.back.entity.Incidencia;

public interface IncidenciaService {
	
	public Incidencia save(Incidencia incidencia);
	
	
	public Incidencia findByMatricula(String matricula);
	
	public List<Incidencia> findByMatriculas(String term);
	
	
	public List<Incidencia> getListaIncidencias();
	

}
