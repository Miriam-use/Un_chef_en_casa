package api.cocinacasa.back.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.cocinacasa.back.dao.UsuarioDetalleDao;
import api.cocinacasa.back.entity.UsuarioDetalle;
import api.cocinacasa.back.service.UsuarioDetalleService;

@Service
public class UsuarioDetalleServiceImp implements UsuarioDetalleService{
	
	@Autowired
	private UsuarioDetalleDao usuarioDetalleDao;


	@Override
	@Transactional
	public UsuarioDetalle save(UsuarioDetalle usuariodetalle) {
		return this.usuarioDetalleDao.save(usuariodetalle);
	}
	@Override
	@Transactional(readOnly=true)
	public UsuarioDetalle findById(Long id) {
		return this.usuarioDetalleDao.findById(id).orElse(null);
	}

}