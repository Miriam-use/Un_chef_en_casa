package api.ecomove.back.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.ecomove.back.dao.ParejaDao;
import api.ecomove.back.entity.Pareja;
import api.ecomove.back.entity.Usuario;
import api.ecomove.back.service.ParejaService;
@Service
public class ParejaServiceImp implements ParejaService{
	
	@Autowired
	private ParejaDao parejadao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pareja> getListaDeParejas() {
		return this.parejadao.findAll();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Pareja findByMatricula(String matricula) {
		return this.parejadao.findByMatricula(matricula);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Pareja findByIdDispositivo(String disp) {
		return this.parejadao.findByIdDispositivo(disp);
	}
	

	@Override
	public void delete(Long id) {
		
		this.parejadao.deleteById(id);
	}



	@Override
	@Transactional
	public Pareja save(Pareja pareja) {
		return this.parejadao.save(pareja);
	}


	@Override
	public Pareja update(String matricula, String dispositivo) {
		findByMatricula(matricula).setIdDispositivo(dispositivo);
		return new Pareja(matricula,dispositivo);
	}


	@Override
	public List<Pareja> findByidentificadorDispositivo(String term) {
		return parejadao.findByidentificadorDispositivo(term);
	}


	@Override
	public Pareja findById(Long id) {
		// TODO Auto-generated method stub
		return this.parejadao.findById(id).orElse(null);
	}




	
}
