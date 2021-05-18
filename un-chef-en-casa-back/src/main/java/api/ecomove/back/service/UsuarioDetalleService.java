package api.ecomove.back.service;

import api.ecomove.back.entity.UsuarioDetalle;

public interface UsuarioDetalleService {
	
	
	public UsuarioDetalle findById(Long id);
	
	public UsuarioDetalle save(UsuarioDetalle usuariodetalle);
	

}