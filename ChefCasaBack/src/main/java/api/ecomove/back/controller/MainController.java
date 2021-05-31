package api.ecomove.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.ecomove.back.entity.Usuario;
import api.ecomove.back.service.UsuarioService;


import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

@RestController
@RequestMapping("/ecomove/v0.1")
@CrossOrigin(origins= {"*"})
public class MainController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	Map<String,Object> response=new HashMap<>();
/*************************************************************************************************************************************/

//	-login
//	-ver mis datos
//	-ver mis viajes
//	-ver detalles del viaje
	
/**Lista de Usuarios***********************************************************************************************************************************/	
	
	@GetMapping("/usuarios")
	public List<Usuario> listaDeUsuarios(){		
		return this.usuarioService.getListaDeUsuarios();
	}	
	
	@GetMapping("/usuarios/pg/{pagina}")
	public Page<Usuario> listaDeUsuarios2(@PathVariable Integer pagina){	
		Pageable pageable=PageRequest.of(pagina, 7);
		return this.usuarioService.findAll(pageable);
	}	
	
/**Get Usuario***********************************************************************************************************************************/
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable Long id){
		Usuario usuario=null;
		try {
			usuario=this.usuarioService.findById(id);
			System.out.println("TODO BIEN GET USUARIO");
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error de acceso a la base de datos");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		if(usuario==null) {
			this.response.put("mensaje","El usuario con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}	
	
/**Get Login***********************************************************************************************************************************/
	
	@GetMapping("/usuarios/login")
	@ResponseBody
	public ResponseEntity<?> getUsuarioLogin(@RequestParam(name="dasatos") String dasatos, @RequestParam String contrasena){
		
		//contrasena=DigestUtils.sha256Hex(contrasena);
		
		System.out.println(DigestUtils.sha256Hex(contrasena));
		
		//contrasena=StringHash.getHashString(contrasena);
		
		Usuario usuario=null;
		try {
			usuario=this.usuarioService.findByDasatosAndContrasena(dasatos, contrasena);
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error de acceso a la base de datos");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		if(usuario==null) {
			this.response.put("mensaje","El usuario de ATOS ".concat(dasatos.concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		this.response.put("mensaje","Acceso concedido");
		this.response.put("usuario",usuario);
		
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.OK);
	}

/**Get Usuario 2***********************************************************************************************************************************/	
	
	@GetMapping("/usuario")
	public Usuario getUsuario2(@RequestBody String requestidjson){		
		JSONObject obj = new JSONObject(requestidjson);
		Long id=obj.getLong("id");
		return this.usuarioService.findById(id);
	}

/**Post create nuevo Usuario***********************************************************************************************************************************/
	
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)//201
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario,BindingResult result){	
		this.response=new HashMap<>();
		Usuario nuevousuario=null;
		if(result.hasErrors()) {			
			List<String>errors=result.getFieldErrors()
					.stream().map(error -> {return "El campo '"+error.getField()+"' "+error.getDefaultMessage();})
					.collect(Collectors.toList());		
			this.response.put("errors",errors);
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.BAD_REQUEST);			
		}
		try {
			nuevousuario=this.usuarioService.save(usuario);
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error al crear el usuario");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje","Usuario creado en base de datos");
		this.response.put("usuario",nuevousuario);
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.CREATED);
	}
	
/**Put update Usuario***********************************************************************************************************************************/
	
	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)//201
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario,BindingResult result,@PathVariable Long id) {
		System.out.println("EDITAR USUARIO");
		Usuario usuariosujeto= this.usuarioService.findById(id);	
		Usuario usuarioactualizado=null;
		if(result.hasErrors()) {
			System.out.println("ERRORES EN UPDATE USUARIO 1");
			List<String>errors=result.getFieldErrors()
					.stream().map(error -> {return "El campo '"+error.getField()+"' "+error.getDefaultMessage();})
					.collect(Collectors.toList());		
			this.response.put("error",errors);
			System.out.println(errors);
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.BAD_REQUEST);			
		}
		if(usuariosujeto==null) {
			System.out.println("ERRORES EN UPDATE USUARIO 2");
			this.response.put("mensaje","El usuario con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.NOT_FOUND);
		}
		try{
			usuariosujeto.setCorreo(usuario.getCorreo());
			usuariosujeto.setContrasena(usuario.getContrasena());
			usuariosujeto.setDasatos(usuario.getDasatos());	
			usuariosujeto.setUsuariodetalle(usuario.getUsuariodetalle());
			usuarioactualizado=this.usuarioService.save(usuariosujeto);
			
		}catch(DataAccessException dae) {
			System.out.println("ERRORES EN UPDATE USUARIO 3");
			this.response.put("mensaje","Error al actualizar el usuario");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje","Usuario editado y actualizado en la base de datos!");
		this.response.put("usuario",usuarioactualizado);
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.CREATED);						
	}
	
/**Delete Usuario***********************************************************************************************************************************/
	
	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)//204
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			this.usuarioService.delete(id);			
		}catch(DataAccessException dae) {
			this.response.put("mensaje","Error al eliminar el usuario");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje","Usuario eliminado de la base de datos");
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.OK);	
	}
	
/**************************************************************************************************************************************************/
	
	//---------------------------------------PRUEBAS---------------------------------------------------
	
	

}