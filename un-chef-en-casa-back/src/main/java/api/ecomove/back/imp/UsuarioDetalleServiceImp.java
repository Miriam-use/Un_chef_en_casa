package api.ecomove.back.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api.ecomove.back.dao.UsuarioDetalleDao;
import api.ecomove.back.entity.UsuarioDetalle;
import api.ecomove.back.service.UsuarioDetalleService;

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