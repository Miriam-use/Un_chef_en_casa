package api.ecomove.back.controller;

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

import org.json.JSONObject;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.ecomove.back.entity.Dispositivo;

import api.ecomove.back.service.DispositivoService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/ecomove/v0.1")
public class DispositivoController {

	// Variables estaticas
	Gson gson = new Gson();

	Map<String, Object> response = new HashMap<>();

	private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	@Value("${urlIot}")
	private String URL;
	@Value("${token}")
	private String token;
	@Value("${instanceOf}")
	private String instanceOf;

	@Autowired
	private DispositivoService dispositivoService;

	// @Secured({"ROLE_ADMIN"})
	@PostMapping("/dispositivo")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> crear(@Valid @RequestBody Dispositivo dispositivo, BindingResult result) throws Exception {

		Dispositivo dispositivonew = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo  '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			dispositivo.setLatitud("0");
			dispositivo.setLongitud("0");
			dispositivo.setVelocidad("0");

			peticionHttpPut(dispositivo);
			dispositivonew = dispositivoService.save(dispositivo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error realizar al insertar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El Dispositivo ha sido creado con éxito!");
		response.put("dispositivo", dispositivonew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	public void peticionHttpPut(Dispositivo dispositivo) throws Exception {

		String nombre = dispositivo.getNombre();
		String instanceOf = this.instanceOf;

		// Mapa con los datos de la conexion
		Map<Object, Object> datos = new HashMap<>();
		datos.put("instanceOf", instanceOf);
		datos.put("simulated", false);
		datos.put("displayName", nombre);
		datos.put("approved", true);

		// Hago un request
		HttpRequest request = HttpRequest.newBuilder().PUT(conversorMapa(datos))
				.uri(URI.create(URL + "preview/devices/" + dispositivo.getIdentificadorDispositivo()))
				.setHeader("Authorization", token).header("Content-Type", "application/json").build();

		// Envio el request
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		// Muestro la respuesta por consola, para ver que ha ido bien
		System.out.println(response.body());
	}

	// Convierte el mapa a un json
	private BodyPublisher conversorMapa(Map<Object, Object> datos) {
		String cadena = gson.toJson(datos);
		return HttpRequest.BodyPublishers.ofString(cadena);
	}

	// ------Conseguir todos los Disp

	@GetMapping("/dispositivos")
	public List<Dispositivo> listaDispositivos() {

		return this.dispositivoService.findAll();
	}

	@GetMapping("/dispositivos/id/{term}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Dispositivo> listaDispositivosID(@PathVariable String term) {

		List<Dispositivo> listaDispositivosID = dispositivoService.findByidentificadorDispositivo(term);

		if (listaDispositivosID.size() == 0) {
			this.response.put("error", "No se encuentra en la base de datos");
			return (List<Dispositivo>) new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
		}

		return listaDispositivosID;

	}
	
	@GetMapping("/dispositivoPorId/id/{term}")
	@ResponseStatus(code = HttpStatus.OK)
	public Dispositivo listadispositivoPorId(@PathVariable String term) {

		Dispositivo dispositivosID = dispositivoService.findByIDDispositivo(term);

		return dispositivosID;

	}

	@GetMapping("/dispositivos/ubicacion/{term}")
	@ResponseStatus(code = HttpStatus.OK)
	public Dispositivo actualizarUbicacion(@PathVariable String term) throws Exception {

		Dispositivo dispositivoU = dispositivoService.findByIdentificadorDisp(term);
		Dispositivo updated = null;

		JSONObject ubicacionnew = peticionHttpGet(dispositivoU);

		dispositivoU.setLatitud((String) ubicacionnew.get("lat"));
		dispositivoU.setLongitud((String) ubicacionnew.get("lon"));

		updated = dispositivoService.save(dispositivoU);

		return updated;

	}

	public JSONObject peticionHttpGet(Dispositivo dispositivo) throws Exception {

		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(URL + "devices/"
				+ dispositivo.getIdentificadorDispositivo() + "/telemetry/Ubicacion?api-version=preview"))
				.setHeader("Authorization", token).build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		String resul = response.body();
		System.out.print(resul);
		JSONObject jsonObject = new JSONObject(resul);

		JSONObject jsonObject2 = new JSONObject();
		jsonObject2 = jsonObject.getJSONObject("value");

		return jsonObject2;
	}

}
