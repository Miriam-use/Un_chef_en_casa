package api.ecomove.back.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.google.gson.Gson;

import api.ecomove.back.entity.Coche;
import api.ecomove.back.entity.Dispositivo;
import api.ecomove.back.entity.Pareja;
import api.ecomove.back.entity.Usuario;
import api.ecomove.back.service.CocheService;
import api.ecomove.back.service.DispositivoService;
import api.ecomove.back.service.ParejaService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/ecomove/v0.1")
@CrossOrigin(origins = { "*" })
public class ParejaController {

	
	@Autowired //----Poner más adelante
	private ParejaService parejaService;
	@Autowired
	private CocheService cocheService;
	@Autowired
	private DispositivoService dispositivoService;
	
	private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    @Value("${urlIot}")
	private String URL;
    @Value("${token}")
	private String token;
    @Value("${instanceOf}")
    private String instanceOf;
    
    Gson gson = new Gson();
	
	
	//Este hashmap sirve para enviar una respuesta a la parte del FrontEnd
		Map<String,Object> response=new HashMap<>();
	
	//--------------------------------------------------METODOS-----------------------------------------
	
		
	@PostMapping("/emparejar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Pareja pareja,BindingResult result){
		this.response = new HashMap<String, Object>();
		Pareja parejanueva = null;
		Coche cochenuevo = null;
		
		//Errores
		if(result.hasErrors()) {
			List<String>errors=result.getFieldErrors()
					.stream().map(error -> {return "El campo '"+error.getField()+"' "+error.getDefaultMessage();})
					.collect(Collectors.toList());
			this.response.put("errors",errors);
		}
		
		try {
			
			if(cocheService.findCocheByMatricula(pareja.getMatricula()) == null ||
			dispositivoService.findByidentificadorDispositivo(pareja.getIdDispositivo()) == null
			) {
				
				System.out.println("ERROR en EMPAREJAR");
				throw new Exception();
				
			}else {
				System.out.println("Creando nueva Pareja");
				parejanueva = this.parejaService.save(pareja);
				cochenuevo=cocheService.findCocheByMatriculas(parejanueva.getMatricula());
				cochenuevo.setIdispositivo(parejanueva.getIdDispositivo());
				
				cocheService.save(cochenuevo);
			}
			
		} catch (DataAccessException e) {
			
			if(parejaService.findByMatricula(pareja.getMatricula())!=null && parejaService.findByIdDispositivo(pareja.getIdDispositivo())!=null) {
				this.response.put("mensaje","El vehículo y el dispositivo seleccionados ya están emparejados");
				//this.response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
				this.response.put("error", "Seleccione otro dispositivo u otro vehículo.");
				return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500
			}else {
				if(parejaService.findByMatricula(pareja.getMatricula())!=null && parejaService.findByIdDispositivo(pareja.getIdDispositivo())==null) {
					this.response.put("mensaje","El vehículo seleccionado ya está emparejado");
					this.response.put("error", "¿Desea emparejar el dispositivo con el vehículo seleccionado?");
					return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.BAD_REQUEST); // Retorna 400
				}else{
					this.response.put("mensaje","El dispositivo seleccionado ya está emparejado");
					this.response.put("error", "¿Desea emparejar el dispositivo con el vehículo seleccionado?");
					return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.BAD_GATEWAY); // Retorna 502
				}
			}
			
			
		
		}catch (Exception e) {
			
			this.response.put("mensaje","Error al emparejar el vehículo con el dispositivo");
			this.response.put("error", "El dispositivo o la matricula son erroneos o no existen.");
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500
		}
		
		
		
		this.response.put("mensaje","El coche ha sido vinculado con éxito");
		this.response.put("pareja",parejanueva);
		vincularIoT(parejanueva.getMatricula(), parejanueva.getIdDispositivo());
		
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.CREATED);	
		
		
		
	}	


	private void vincularIoT(String matricula, String id) {
		// TODO Auto-generated method stub
		
		Dispositivo dispositivonew = null;
		Map<String, Object> response = new HashMap<>();
		
		
		String nombre = matricula;
		String instanceOf = this.instanceOf;
		
    	//Mapa con los datos de la conexion
        Map<Object, Object> datos = new HashMap<>();
        datos.put("Matricula", matricula);
        
        //Hago un request
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(conversorMapa(datos))
                .uri(URI.create(URL+"/preview/devices/"+id+"/properties"))
                .setHeader("Authorization", token)
                .header("Content-Type", "application/json")
                .build();
        
        //Envio el request
        try {
			HttpResponse<String> respuesta = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Muestro la respuesta por consola, para ver que ha ido bien
        //System.out.println(respuesta.body());
		
		
		
		
	}
	
	private BodyPublisher conversorMapa(Map<Object, Object> datos) {
		String cadena = gson.toJson(datos);
        return HttpRequest.BodyPublishers.ofString(cadena);
	}


	public ResponseEntity<?> getDispositivo(@PathVariable String matricula){
		Pareja pareja = null;
		
		try {
			pareja = this.parejaService.findByMatricula(matricula);
			System.out.println("Vehiculo encontrado");
			
			
			
		} catch (DataAccessException e) {
			this.response.put("mensaje","Error de acceso a la base de datos");
			this.response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		
//		if(matricula==null) {
//			this.response.put("mensaje","El vehiculo con matrícula ".concat(matricula.concat(" no se encuentra en el sistema")));
//			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
//		}
		
		return  new ResponseEntity<Pareja>(pareja,HttpStatus.OK);
	}
	
	
	@GetMapping("/emparejar/all")
	public List<Pareja> listaDeParejas(){		
		return this.parejaService.getListaDeParejas();
	}
	
	
	@GetMapping("/emparejar/matricula/{term}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Pareja> listaDispositivosID(@PathVariable String term){
		
		List<Pareja> listaDispositivosID = parejaService.findByidentificadorDispositivo(term);
		
		
		if(listaDispositivosID.size()==0) {
			this.response.put("error","No se encuentra en la base de datos");
			return (List<Pareja>) new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		
		return listaDispositivosID;
		
	}
	
	@PutMapping("/emparejar/editar")
	public ResponseEntity<?> update(@Valid @RequestBody Pareja pareja, BindingResult result){		
		
		System.out.println(pareja.getMatricula()+"   "+pareja.getIdDispositivo());
		Pareja parejaVehiculoActual = parejaService.findByMatricula(pareja.getMatricula());
		Pareja parejaDispositivoActual = parejaService.findByIdDispositivo(pareja.getIdDispositivo());
		
		
		Pareja parejaActualizada = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo  '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		if (parejaVehiculoActual == null) {
			try {
				
				parejaDispositivoActual.setMatricula(pareja.getIdDispositivo());
				
				parejaActualizada = parejaService.save(parejaDispositivoActual);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar en la base de datos!");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}else if(parejaDispositivoActual == null) {
			try {
				
				parejaVehiculoActual.setIdDispositivo(pareja.getIdDispositivo());
				
				parejaActualizada = parejaService.save(parejaVehiculoActual);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar en la base de datos!");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}
		
		response.put("mensaje", "Actualización realizada con éxito!");	
		response.put("coche", parejaActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/emparejar/{id}")
	public ResponseEntity<?> getParejas(@PathVariable Long id){
		Pareja pareja=null;
		try {
			pareja=this.parejaService.findById(id);
			System.out.println("TODO BIEN GET PAREJAS");
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error de acceso a la base de datos");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		if(pareja==null) {
			this.response.put("mensaje","El coche con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<Pareja>(pareja,HttpStatus.OK);
	}
	
	@DeleteMapping("/pareja/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)//204
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Pareja pareja = parejaService.findById(id);
		Coche cochenuevo = null;
		
		try {
			this.parejaService.delete(id);	
			
			cochenuevo = cocheService.findCocheByMatriculas(pareja.getMatricula());
			cochenuevo.setIdispositivo(null);
			
			cocheService.save(cochenuevo);
						
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error al eliminar la pareja");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje","Pareja eliminado de la base de datos");
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.OK);	
	}
	
	
}
