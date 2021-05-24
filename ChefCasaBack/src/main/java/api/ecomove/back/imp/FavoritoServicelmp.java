package api.ecomove.back.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.ecomove.back.dao.FavoritoDao;
import api.ecomove.back.entity.Favorito;
import api.ecomove.back.service.FavoritoService;

@Service
public class FavoritoServicelmp implements FavoritoService {
	
	@Autowired
	private FavoritoDao favoritodao;

	@Override
	@Transactional
	public Favorito save(Favorito favorito) {
		return this.favoritodao.save(favorito);
	}

	@Override
	public List<Favorito> findAll() {
		return this.favoritodao.findAll();
	}

	@Override
	public void delete(Long id) {
		this.favoritodao.deleteById(id);
	}

}
