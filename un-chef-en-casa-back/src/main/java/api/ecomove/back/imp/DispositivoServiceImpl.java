package api.ecomove.back.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.ecomove.back.dao.DispositivoDao;
import api.ecomove.back.entity.Dispositivo;
import api.ecomove.back.service.DispositivoService;

@Service
public class DispositivoServiceImpl implements DispositivoService{

	
	@Autowired
	private DispositivoDao dispositivoDao;
	
	@Override
	@Transactional(readOnly = true)
	public Dispositivo findBynombre(String nombre) {
	 return dispositivoDao.findBynombre(nombre);
	}

	@Override
	@Transactional
	public Dispositivo save(Dispositivo dispositivo) {
		return dispositivoDao.save(dispositivo);
	}

	@Override
	public List<Dispositivo> findByidentificadorDispositivo(String id) {
		
		return dispositivoDao.findByidentificadorDispositivo(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Dispositivo> findAll() {
		
		return dispositivoDao.findAll();
	}

	@Override
	public Dispositivo findByIdentificadorDisp(String id) {
		
		return dispositivoDao.findByIdentificadorDisp(id);
	}

	@Override
	public Dispositivo findByIDDispositivo(String iddispositivo) {
		// TODO Auto-generated method stub
		return dispositivoDao.findByIDDispositivo(iddispositivo);
	}


}
