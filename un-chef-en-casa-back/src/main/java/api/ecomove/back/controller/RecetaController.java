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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.ecomove.back.entity.Receta;
import api.ecomove.back.service.RecetaService;

@RestController
@RequestMapping("/ecomove/v0.1")
@CrossOrigin(origins = { "*" })
public class RecetaController {
	
	@Autowired
	private RecetaService recetaService;
	
	//Este hashmap sirve para enviar una respuesta a la parte del FrontEnd
	Map<String,Object> response=new HashMap<>();
	
	//---------------------------------CREACION DE RECETA EN LA BASE DE DATOS---------------------------------
	
	@PostMapping("/receta")
	@ResponseStatus(HttpStatus.CREATED) // Retorna 201
	public ResponseEntity<?> create(@Valid @RequestBody Receta receta,BindingResult result){
		this.response = new HashMap<>();
		Receta recetaNuevo = null;
		//En el caso de que tenga errores a la hora de postear, mandara un listado con dichos errores
		if(result.hasErrors()) {
			List<String>errors=result.getFieldErrors()
					.stream().map(error -> {return "El campo '"+error.getField()+"' "+error.getDefaultMessage();})
					.collect(Collectors.toList());
			this.response.put("errors",errors);
		}
		
		try {
			recetaNuevo=this.recetaService.save(receta);
		}catch(DataAccessException dataEx) {
			this.response.put("mensaje","Error al insertar la receta en la base de datos");
			this.response.put("error", dataEx.getMessage().concat(" ").concat(dataEx.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500
		}
		
		this.response.put("mensaje","La receta ha sido insertado en la base de datos");
		this.response.put("coche",recetaNuevo);
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.CREATED);
	}
	
	//--------------------------------Actualiza la informacion----------------------------------------
	
	@PutMapping("/recetaupdate/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Receta receta, BindingResult result, @PathVariable Long id) {
		Receta recetaActual = recetaService.findById(id);

		Receta recetaUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo  '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (recetaActual == null) {
			response.put("mensaje", "Error , no se pudo editar, coche ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			recetaActual.setTitulo(receta.getTitulo());
			recetaActual.setDetalle(receta.getDetalle());
			recetaActual.setTiempo(receta.getTiempo());
			recetaActual.setMesas(receta.getMesas());
			recetaActual.setLista(receta.getLista());
			recetaActual.setPasos(receta.getPasos());
			
			recetaUpdated = recetaService.save(recetaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La receta ha sido actualizado con éxito!");	
		response.put("coche", recetaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	//--------------------------------------Conseguir receta para la lista------------------------------------
	
	@GetMapping("/receta/all")	
	public List<Receta> listarReceta(){
			return this.recetaService.findAll();
	}
	
	@GetMapping("/receta-id/{id}")
	public ResponseEntity<?> getReceta(@PathVariable Long id){
		Receta receta=null;
		
		try {
			receta=this.recetaService.findById(id);
			System.out.println("TODO BIEN GET RECETA");
			
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error de acceso a la base de datos");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		if(receta==null) {
			this.response.put("mensaje","La receta con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<Receta>(receta,HttpStatus.OK);
	}
	
	@GetMapping("/receta/filtro/{term}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Receta> filtrarrecetaTitulo(@PathVariable String term){
		
		List<Receta> listaReceta = recetaService.findCocheByTitulo(term);
		
		if(listaReceta.size()==0) {
			this.response.put("error","No se encuentra en la base de datos");
			return (List<Receta>) new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		
		return listaReceta;
		
	}
	
	//-------------------------------------------------eliminar receta-----------------------------------
	
	@DeleteMapping("/receta/baja/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)//204
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		try {
			this.recetaService.delete(id);	
						
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error al eliminar la receta");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje","Receta eliminado de la base de datos");
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.OK);	
	}

}
