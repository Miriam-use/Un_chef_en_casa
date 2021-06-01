package api.cocinacasa.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.cocinacasa.back.entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.dasatos=?1 and u.contrasena=?2")
	public Usuario findByDasatosAndContrasena(String dasatos, String contrasena);
	
	@Query("select u from Usuario u where u.id like %?1%")
	public List<Usuario> findIdentificadorByUsuario(Long id);
	
	/*
	 * @Query("select u from Usuario u where u.dasatos=?1 and u.contrasena=?2" )
	 * public Usuario findByDasatosAndContrasena(String dasatos, String contrasena);
	 * 
	 * @Query("select u from Usuario u where u.dasatos=?") public Usuario
	 * findByDasatos(String dasatos);
	 */
	
}