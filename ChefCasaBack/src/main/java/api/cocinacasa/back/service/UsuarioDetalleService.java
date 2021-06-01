package api.cocinacasa.back.service;

import api.cocinacasa.back.entity.UsuarioDetalle;

public interface UsuarioDetalleService {
	
	
	public UsuarioDetalle findById(Long id);
	
	public UsuarioDetalle save(UsuarioDetalle usuariodetalle);
	

}