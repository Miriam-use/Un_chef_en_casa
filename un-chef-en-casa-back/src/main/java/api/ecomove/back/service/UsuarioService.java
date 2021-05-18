package api.ecomove.back.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import api.ecomove.back.entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario>getListaDeUsuarios();
	
	public Page<Usuario>findAll(Pageable pageable);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	public Usuario findByDasatosAndContrasena(String dasatos, String contrasena);
	
	//public Usuario findByDasatos(String dasatos);
		
}