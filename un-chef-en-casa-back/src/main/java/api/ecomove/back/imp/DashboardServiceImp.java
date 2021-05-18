package api.ecomove.back.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.ecomove.back.dao.DashboardDao;
import api.ecomove.back.entity.Viaje;
import api.ecomove.back.service.DashboardService;

@Service
public class DashboardServiceImp implements DashboardService{
	
	@Autowired
	private DashboardDao dashboardDao;

	@Override
	public Map<String,String> getDatosDashboard(Long usuarioid) {
		return this.dashboardDao.findDatosDashboard(usuarioid);
	}

	@Override
	public List<Viaje> getViajesEcologicos(Long usuarioid) {
		return this.dashboardDao.findViajesEcologicos(usuarioid);
	}

	@Override
	public List<Viaje> getViajesPorUsuarioCompletados(Long usuarioid) {
		return this.dashboardDao.findViajesUsuarioCompletados(usuarioid);
	}




	
}