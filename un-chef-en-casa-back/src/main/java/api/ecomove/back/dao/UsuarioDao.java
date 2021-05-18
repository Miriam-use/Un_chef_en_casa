package api.ecomove.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.ecomove.back.entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.dasatos=?1 and u.contrasena=?2")
	public Usuario findByDasatosAndContrasena(String dasatos, String contrasena);
	
	/*
	 * @Query("select u from Usuario u where u.dasatos=?1 and u.contrasena=?2" )
	 * public Usuario findByDasatosAndContrasena(String dasatos, String contrasena);
	 * 
	 * @Query("select u from Usuario u where u.dasatos=?") public Usuario
	 * findByDasatos(String dasatos);
	 */
	
}