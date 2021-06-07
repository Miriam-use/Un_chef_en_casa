package api.cocinacasa.back.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.cocinacasa.back.dao.DashboardDao;
import api.cocinacasa.back.service.DashboardService;

@Service
public class DashboardServiceImp implements DashboardService{
	
	@Autowired
	private DashboardDao dashboardDao;

	@Override
	public Map<String,String> getDatosDashboard(Long usuarioid) {
		return this.dashboardDao.findDatosDashboard(usuarioid);
	}




	
}