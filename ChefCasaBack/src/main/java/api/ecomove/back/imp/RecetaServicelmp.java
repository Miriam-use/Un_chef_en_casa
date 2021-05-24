package api.ecomove.back.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.ecomove.back.dao.RecetaDao;
import api.ecomove.back.entity.Receta;
import api.ecomove.back.service.RecetaService;

@Service
public class RecetaServicelmp implements RecetaService {
	
	@Autowired
	private RecetaDao receDao;

	@Override
	@Transactional(readOnly=true)
	public List<Receta> findAll() {
		return this.receDao.findAll();
	}

	@Override
	@Transactional
	public Receta save(Receta receta) {
		return this.receDao.save(receta);
	}

	@Override
	@Transactional(readOnly=true)
	public Receta findById(Long id) {
		return this.receDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.receDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Receta> findCocheByTitulo(String term) {
		return receDao.findCocheByTitulo(term);
	}

	@Override
	public Receta findRecetaByTitulo(String term) {
		return receDao.findRecetaByTitulo(term);
	}

	@Override
	public Receta findRecetaById(String term) {
		return receDao.findRecetaById(term);
	}
	
	

}
