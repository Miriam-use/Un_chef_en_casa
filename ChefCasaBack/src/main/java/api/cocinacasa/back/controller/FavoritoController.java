package api.cocinacasa.back.controller;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import api.cocinacasa.back.entity.Favorito;
import api.cocinacasa.back.entity.Receta;
import api.cocinacasa.back.service.FavoritoService;
import api.cocinacasa.back.service.RecetaService;

@RestController
@RequestMapping("/ecomove/v0.1")
@CrossOrigin(origins = { "*" })
public class FavoritoController {
	
	@Autowired
	private FavoritoService favoritoservice;
	
	@Autowired
	private RecetaService recetaService;
	
	
	//Este hashmap sirve para enviar una respuesta a la parte del FrontEnd
		Map<String,Object> response=new HashMap<>();
		
	//----------------------------------------crear favorito---------------------------------------------------------
		@PostMapping("/favorito")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<?> create(@Valid @RequestBody Favorito favorito ,BindingResult result){
			this.response = new HashMap<>();
			Favorito favoritoNuevo = null;
			
			//En el caso de que tenga errores a la hora de postear, mandara un listado con dichos errores
			if(result.hasErrors()) {
				List<String>errors=result.getFieldErrors()
						.stream().map(error -> {return "El campo '"+error.getField()+"' "+error.getDefaultMessage();})
						.collect(Collectors.toList());
				this.response.put("errors",errors);
			}
			
			try {
				
					favoritoNuevo=this.favoritoservice.save(favorito);
				
			}catch(DataAccessException dataEx) {
				this.response.put("mensaje","Error al insertar la receta en la base de datos");
				this.response.put("error", dataEx.getMessage().concat(" ").concat(dataEx.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500
			}
			
			this.response.put("mensaje","La receta ha sido insertado en la base de datos");
			this.response.put("favorito",favoritoNuevo);
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.CREATED);
		}
	
		//------------------------------------eliminar favorito--------------------------------------------
		@DeleteMapping("/favorito/baja/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)//204
		public ResponseEntity<?> delete(@PathVariable Long id) {
			
			try {
				this.favoritoservice.delete(id);	
							
			}catch(DataAccessException dae) {
				this.response.put("mensaje","Error al eliminar favorito");
				this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			this.response.put("mensaje","Favorito eliminado de la base de datos");
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.OK);	
		}
		
		
		//------------------------------------busqueda favorito--------------------------------------------
		@GetMapping("/favorito/all")	
		public List<Favorito> listarFavorito(){
				return this.favoritoservice.findAll();
		}

}
