package api.ecomove.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.ecomove.back.entity.UsuarioDetalle;

@Repository
public interface UsuarioDetalleDao extends JpaRepository<UsuarioDetalle, Long>{
	


}