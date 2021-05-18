package api.ecomove.back.service;

import java.util.List;
import java.util.Map;

import api.ecomove.back.entity.Viaje;

public interface DashboardService {
	
	public Map<String,String> getDatosDashboard(Long usuarioid);
	
	public List<Viaje>getViajesEcologicos(Long usuarioid);
	
	public List<Viaje>getViajesPorUsuarioCompletados(Long usuarioid);
		
	
}