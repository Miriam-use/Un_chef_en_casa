package api.ecomove.back.controller;


import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import api.ecomove.back.entity.Coche;
import api.ecomove.back.entity.Pareja;
import api.ecomove.back.service.CocheService;
import api.ecomove.back.service.ParejaService;

@RestController
@RequestMapping("/ecomove/v0.1")
@CrossOrigin(origins = { "*" })
public class CocheController {
	
	@Autowired
	private CocheService cocheService;
	
	private Pareja pareja;
	
	@Autowired
	private ParejaService parejaService;
	
	//Este hashmap sirve para enviar una respuesta a la parte del FrontEnd
	Map<String,Object> response=new HashMap<>();
	
//---------------------------------CREACION DEL COCHE EN LA BASE DE DATOS---------------------------------
	@PostMapping("/coche")
	@ResponseStatus(HttpStatus.CREATED) // Retorna 201
	public ResponseEntity<?> create(@Valid @RequestBody Coche coche,BindingResult result){
		this.response = new HashMap<>();
		Coche cocheNuevo = null;
		//En el caso de que tenga errores a la hora de postear, mandara un listado con dichos errores
		if(result.hasErrors()) {
			List<String>errors=result.getFieldErrors()
					.stream().map(error -> {return "El campo '"+error.getField()+"' "+error.getDefaultMessage();})
					.collect(Collectors.toList());
			this.response.put("errors",errors);
		}
		
		try {
			coche.setActivo(true);
			cocheNuevo=this.cocheService.save(coche);
		}catch(DataAccessException dataEx) {
			this.response.put("mensaje","Error al insertar el coche en la base de datos");
			this.response.put("error", dataEx.getMessage().concat(" ").concat(dataEx.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500
		}
		
		this.response.put("mensaje","El coche ha sido insertado en la base de datos");
		this.response.put("coche",cocheNuevo);
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.CREATED);
	}
	
	//@Secured("ROLE_ADMIN")
	//--- Para realizar la baja solo modifica el atributo activo a false
	@PutMapping("/cocheBaja/{id}")
	public ResponseEntity<?> baja(@Valid @RequestBody Coche coche, BindingResult result, @PathVariable Long id) {
		Coche cocheActual = cocheService.findById(id);

		Coche cocheUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo  '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (cocheActual == null) {
			response.put("mensaje", "Error , no se pudo dar de baja, coche ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
				cocheActual.setActivo(false);
				cocheUpdated = cocheService.save(cocheActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El coche fue dado de baja con éxito!");
		response.put("coche", cocheUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
     //----Actualiza la informacion del coche
	@PutMapping("/coche/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Coche coche, BindingResult result, @PathVariable Long id) {
		Coche cocheActual = cocheService.findById(id);

		Coche cocheUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo  '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (cocheActual == null) {
			response.put("mensaje", "Error , no se pudo editar, coche ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			cocheActual.setMatricula(coche.getMatricula());
			cocheActual.setModelo(coche.getModelo());
			cocheActual.setEtiqueta(coche.getEtiqueta());
			
			cocheUpdated = cocheService.save(cocheActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El coche ha sido actualizado con éxito!");	
		response.put("coche", cocheUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	//@Secured({"ROLE_ADMIN"})
	@GetMapping("/coche/coche-matricula/{term}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Coche> filtrarCocheMatricula(@PathVariable String term){
		
		List<Coche> listaCochesMatricula = cocheService.findCocheByMatricula(term);
		
		if(listaCochesMatricula.size()==0) {
			this.response.put("error","No se encuentra en la base de datos");
			return (List<Coche>) new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		
		return listaCochesMatricula;
		
	}
	
	@GetMapping("/coche/coche-matricula-emparejado/{term}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Coche> cocheMatriculaEmparejado(@PathVariable String term){
		
		List<Coche> listaCochesMatricula = cocheService.findCocheByMatricula(term);  //lista de los coches por matricula
		List<Pareja> listaParejas = parejaService.getListaDeParejas(); //lista de todas las parejas
		List<Coche> listaSinEMparejar=new ArrayList<Coche>();
		boolean emparejado=false;
		int cont=0;


		if(listaCochesMatricula.size()>0) {
			for(int i=0; i<listaCochesMatricula.size();i++) {
				String matricula = listaCochesMatricula.get(i).getMatricula();
				for(int j=0; j<listaParejas.size();j++) { 
					if(matricula.equals(listaParejas.get(j).getMatricula())) {
						emparejado=true;
						/*Coche coche = new Coche(); coche.setMatricula(matriculaSinEmparejar);
						listaSinEMparejar.add(coche);
						for(int x=0;x<listaSinEMparejar.size();x++) {
							System.out.println(listaSinEMparejar.get(x).getMatricula());
						}
						cont++;
						*/
					}
				}
				if(emparejado==false) {
					//listaSinEMparejar set matricula = matricula
					cont++;
				}
			}
			//this.response.put("error","No se encuentra en la base de datos");
			//return (List<Coche>) new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
			return listaSinEMparejar;
		}
		
		return listaSinEMparejar;
		
	}
	
	@GetMapping("/coche/coche-modelo/{term}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Coche> filtrarCocheModelo(@PathVariable String term){
		
		
		return cocheService.findCocheByModelo(term);
		
	}
	
	@GetMapping("/coche/coche-etiqueta/{term}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Coche> filtrarCocheEtiqueta(@PathVariable String term){
		
		
		return cocheService.findCocheByEtiqueta(term);
		
	}
	

	
	//----------Conseguir coches para la lista
	
	@GetMapping("/coches")	
	public List<Coche> listarVehiculos(){
			return this.cocheService.findAll();
	}
		

	@GetMapping("/coche/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable Long id){
		Coche coche=null;
		
		try {
			coche=this.cocheService.findById(id);
			System.out.println("TODO BIEN GET USUARIO");
			
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error de acceso a la base de datos");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		if(coche==null) {
			this.response.put("mensaje","El coche con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<Coche>(coche,HttpStatus.OK);
	}
	

}
