package api.ecomove.back.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.ecomove.back.dao.ViajeDao;
import api.ecomove.back.entity.Proveedor;
import api.ecomove.back.entity.Viaje;
import api.ecomove.back.service.ViajeService;

@Service
public class ViajeServiceImp implements ViajeService{
	
	@Autowired
	private ViajeDao viajeDao;

	@Override
	@Transactional(readOnly=true)
	public List<Viaje> findAll() {
		return this.viajeDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Viaje findById(Long id) {
		return this.viajeDao.findById(id).orElse(null);
	}	

	@Override
	public Viaje findByRideId(Long rideid) {
		return this.viajeDao.findByRideId(rideid);
	}

	@Override
	@Transactional
	public Viaje save(Viaje viaje) {
		return this.viajeDao.save(viaje);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.viajeDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Proveedor> findAllProveedores() {
		return this.viajeDao.findAllProveedores();
	}

	@Override
	public List<Viaje> getViajesPorUsuario(Long usuarioid) {
		return this.viajeDao.findViajesPorUsuario(usuarioid);
	}

	@Override
	public List<Viaje> getViajesFiltrados(String idusuario, String status, String fechaida, String proveedor, String origen, String destino) {		
		return this.viajeDao.findViajesFiltrados(idusuario, status, fechaida, proveedor, origen, destino);
	}

	@Override
	public Page<Viaje> getMisViajesFiltradosPaginados(String usuarioid, String status, String fechaida, String proveedor, String origen, String destino, Pageable pageable) {	
		return this.viajeDao.findMisViajesFiltradosPaginados(usuarioid, status, fechaida, proveedor, origen, destino, pageable);
	}
	
	// pruebas ****************
	@Override
	public Page<Viaje> findMisViajesPaginados(Long usuarioid, Pageable pageable) {
		return this.viajeDao.findMisViajesPaginado(usuarioid, pageable);
	}

	@Override
	public Page<Viaje> findMisViajesPaginados2(int pageNumber, int numberOfElementsPerPage) {
		return this.viajeDao.findAll(PageRequest.of(pageNumber,numberOfElementsPerPage));
	}
	//************************
}