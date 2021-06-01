package api.cocinacasa.back.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.cocinacasa.back.dao.UsuarioDao;
import api.cocinacasa.back.entity.Usuario;
import api.cocinacasa.back.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> getListaDeUsuarios() {
		return this.usuarioDao.findAll();
	}
	
	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return this.usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findById(Long id) {
		return this.usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return this.usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.usuarioDao.deleteById(id);
		
	}

	@Override
	public Usuario findByDasatosAndContrasena(String dasatos, String contrasena) {		
		return this.usuarioDao.findByDasatosAndContrasena(dasatos, contrasena);
	}

	@Override
	@Transactional
	public List<Usuario> findIdentificadorByUsuario(Long id) {
		return usuarioDao.findIdentificadorByUsuario(id);
	}
}