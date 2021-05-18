package api.ecomove.back.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.ecomove.back.dao.IncidenciaDao;
import api.ecomove.back.entity.Incidencia;
import api.ecomove.back.service.IncidenciaService;

@Service
public class IncidenciaServiceImp implements IncidenciaService{

	
	@Autowired
	private IncidenciaDao incidenciaDao;

	@Override
	public Incidencia save(Incidencia incidencia) {
		// TODO Auto-generated method stub
		return this.incidenciaDao.save(incidencia);
	}

	@Override
	public Incidencia findByMatricula(String matricula) {
		// TODO Auto-generated method stub
		return this.incidenciaDao.findByMatricula(matricula);
	}

	@Override
	public List<Incidencia> getListaIncidencias() {
		// TODO Auto-generated method stub
		return this.incidenciaDao.findAll();
	}

	@Override
	public List<Incidencia> findByMatriculas(String term) {
		// TODO Auto-generated method stub
		return this.incidenciaDao.findByMatriculas(term);
	}
	
}
