package api.ecomove.back.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.ecomove.back.dao.CocheDao;
import api.ecomove.back.entity.Coche;
import api.ecomove.back.service.CocheService;

@Service
public class CocheServiceImp implements CocheService {
	
	@Autowired
	private CocheDao cocheDao;

	@Override
	@Transactional(readOnly=true)
	public List<Coche> findAll() {
		return this.cocheDao.findAll();
	}

	@Override
	@Transactional
	public Coche save(Coche coche) {
		return this.cocheDao.save(coche);
	}

	@Override
	@Transactional(readOnly=true)
	public Coche findById(Long id) {
		return this.cocheDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		this.cocheDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Coche> findCocheByMatricula(String term) {
		return cocheDao.findCocheByMatricula(term);
	}

	@Override
	@Transactional
	public List<Coche> findCocheByModelo(String term) {
		return cocheDao.findCocheByModelo(term);
	}

	@Override
	@Transactional
	public List<Coche> findCocheByEtiqueta(String term) {
	  return cocheDao.findCocheByEtiqueta(term);
	}

	@Override
	public Coche findCocheByMatriculas(String term) {
	
		return cocheDao.findCocheByMatriculas(term);
	}

}
