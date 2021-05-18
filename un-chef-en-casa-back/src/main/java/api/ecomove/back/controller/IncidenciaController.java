package api.ecomove.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.ecomove.back.entity.Coche;
import api.ecomove.back.entity.Incidencia;
import api.ecomove.back.service.IncidenciaService;

@RestController
@RequestMapping("/ecomove/v0.1")
@CrossOrigin(origins = { "*" })
public class IncidenciaController {
	
	
	@Autowired
	private IncidenciaService incidenciaService;
	
	Map<String,Object> response=new HashMap<>();
	
	
	@PostMapping("/incidencia/save")
	public ResponseEntity<?> create(@Valid @RequestBody Incidencia incidencia,BindingResult result){
		
		Incidencia incidenciaNueva = null;
		
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo  '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			incidenciaNueva = incidenciaService.save(incidencia);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear una nueva incidencia");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "Incidencia insertada con éxito");
		response.put("incidencia", incidenciaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/incidencia/findall")
	public List<Incidencia> listadeIncidencias(){
		
		return incidenciaService.getListaIncidencias();
	}
	
	@GetMapping("/incidencia/findmatricula")
	public ResponseEntity<?> findIncidencia(@PathVariable String  matricula) {
		
		Map<String,Object> response = new HashMap<>();
		Incidencia inci;
		
		try {
			inci = this.incidenciaService.findByMatricula(matricula);
			
		} catch (DataAccessException e) {
			response.put("mensaje","Error de acceso a la base de datos");
			response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		if(inci == null) {
			response.put("mensaje","La incidencia del coche:".concat(matricula.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}else {
			
			return new ResponseEntity<Incidencia>(inci,HttpStatus.OK);
		}
		
	}
	
		@GetMapping("/incidencia/matricula/{term}")
		@ResponseStatus(code=HttpStatus.OK)
		public List<Incidencia> filtrarCocheMatricula(@PathVariable String term){
			
			List<Incidencia> listaIncidencia = incidenciaService.findByMatriculas(term);
			
			if(listaIncidencia.size()==0) {
				this.response.put("error","No se encuentra en la base de datos");
				return (List<Incidencia>) new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
			}
			
			return listaIncidencia;
			
		}
	
	
	
	
	

}
