package api.ecomove.back.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.TransactionException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.ecomove.back.entity.Proveedor;
import api.ecomove.back.entity.Viaje;
import api.ecomove.back.service.ViajeService;
import api.ecomove.back.util.JsonHash;;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/ecomove/v0.1")
@CrossOrigin(origins = { "*" })
public class ViajeController {

	@Autowired
	private ViajeService viajeService;

	Map<String, Object> response = new HashMap<>();

/*** Get List y viaje ***********************************************************************************************************************************/

	@GetMapping("/viajes")
	public List<Viaje> listaDeViajes() {
		return this.viajeService.findAll();
	}

	@GetMapping("/viajes/{id}")
	public Viaje getViaje(@PathVariable Long id) {
		return this.viajeService.findById(id);
	}

/*** Get Viajes por Usuario***********************************************************************************************************************************/

	// viajes por usuario
	@GetMapping("/viajesusuario/{usuarioid}")
	public List<Viaje> getViajesPorUsuario(@PathVariable Long usuarioid) {
		return this.viajeService.getViajesPorUsuario(usuarioid);
	}

	// viajes paginados
	@GetMapping("/viajesusuariopag")
	public Page<Viaje> getMisViajesPaginado(@RequestParam String id, @RequestParam String pagina) {
		System.out.println(id + "  " + pagina);

//		Example<Viaje> spec=Example.of(this.viajeService.findById(Long.parseLong(id)));

//		System.out.println(spec.getProbe().getRideid());		

		Pageable pageable = PageRequest.of(Integer.parseInt(pagina), 4);

		Page<Viaje> pageViajes = this.viajeService.findMisViajesPaginados(Long.parseLong(id), pageable);

		System.out.println(pageViajes);

		return pageViajes;
	}

	// viajes filtrados
	@GetMapping("/viajesusuario")
	public List<Viaje> getViajesPorUsuarioFiltrado(@RequestParam String id, @RequestParam String status,
			@RequestParam String fechaida, @RequestParam String proveedor, @RequestParam String origen,
			@RequestParam String destino) {

		status = status.equalsIgnoreCase("undefined") ? "%" : status;
		fechaida = fechaida.equalsIgnoreCase("undefined") ? "%" : fechaida;
		proveedor = proveedor.equalsIgnoreCase("undefined") ? "%" : proveedor;
		origen = origen.equalsIgnoreCase("undefined") ? "%" : origen;
		destino = destino.equalsIgnoreCase("undefined") ? "%" : destino;

		System.out.println(id + " " + status + " " + fechaida + " " + proveedor + " " + origen + " " + destino);

		return this.viajeService.getViajesFiltrados(id, status, fechaida, proveedor, origen, destino);
	}

	// viajes por usuario filtrados y paginados
	@GetMapping("/viajesusuariopag3")
	public Page<Viaje> getMisViajesPaginado3(@RequestParam String id, @RequestParam String pagina,
			@RequestParam String status, @RequestParam String fechaida, @RequestParam String proveedor,
			@RequestParam String origen, @RequestParam String destino) {

		status = status.equalsIgnoreCase("undefined") ? "%" : status;
		fechaida = fechaida.equalsIgnoreCase("undefined") ? "%" : fechaida;
		proveedor = proveedor.equalsIgnoreCase("undefined") ? "%" : proveedor;
		origen = origen.equalsIgnoreCase("undefined") ? "%" : origen;
		destino = destino.equalsIgnoreCase("undefined") ? "%" : destino;

		System.out.println(id + " " + status + " " + fechaida + " " + proveedor + " " + origen + " " + destino);

		Pageable pageable = PageRequest.of(Integer.parseInt(pagina), 4);

		Page<Viaje> pageViajes = this.viajeService.getMisViajesFiltradosPaginados(id, status, fechaida, proveedor,
				origen, destino, pageable);

		System.out.println(pageViajes);

		return pageViajes;

	}

	// pruebas
	// *************************************************************************************************************************************

	@GetMapping("/viajesusuariopag2")
	public Page<Viaje> getMisViajesPaginado2(@RequestParam String pagina, @RequestParam String viajesporpagina) {

		Page<Viaje> pageViajes = this.viajeService.findMisViajesPaginados2(Integer.parseInt(pagina),
				Integer.parseInt(viajesporpagina));

		System.out.println(pageViajes);

		return pageViajes;

	}
	// *******************************************************************************************************************************************

/*** Post nuevo viaje ***********************************************************************************************************************************/

	@PostMapping("/viaje")
	@ResponseStatus(HttpStatus.CREATED) // 201
	public Viaje create(@RequestBody Viaje viaje) {

		Date fecha = viaje.getFechaida();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, 1); // numero de días a añadir, o restar en caso de días<0

		System.out.println(calendar.getTime()); // Devuelve el objeto Date con los nuevos días añadidos

		viaje.setFechaida(calendar.getTime());

		return this.viajeService.save(viaje);
	}

/*** Patch update Viaje ***********************************************************************************************************************************/

	@PatchMapping("/viaje")
	@ResponseStatus(HttpStatus.CREATED) // 201
	public ResponseEntity<?> update(@Valid @RequestBody String recogida, BindingResult result) {

		JSONObject recogidajson = new JSONObject(recogida);

		JSONObject viaje = recogidajson.getJSONObject("viaje");

		ObjectMapper mapper = new ObjectMapper();

		String responseHash = recogidajson.toString();

		try {
			responseHash = JsonHash.getHash(mapper, responseHash);
			System.out.println(responseHash);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {

		}

		Viaje viajesujeto = this.viajeService.findByRideId(viaje.getLong("ride_table_id"));
		Viaje viajeactualizado = null;
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(error -> {
				return "El campo '" + error.getField() + "' " + error.getDefaultMessage();
			}).collect(Collectors.toList());
			this.response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.BAD_REQUEST);
		}
		if (viajesujeto == null) {
			Long id = viaje.getLong("ride_table_id");
			this.response.put("mensaje",
					"El viaje con el Id ".concat(id.toString().concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
		}
		try {
			viajesujeto.setStatus(viaje.getString("status"));
			if (viajesujeto.getStatus().equalsIgnoreCase("completed")) {
				System.out.println(viaje.getJSONObject("receipt"));
				viajesujeto.setIdreceipt(viaje.getJSONObject("receipt").getString("idreceipt"));
				viajesujeto.setPrecio(Float.parseFloat(viaje.getJSONObject("receipt").getString("subtotal")));
				// en función de los datos de Uber sobre el vehículo, sacar el consumo y el
				// factor de emisión
				viajesujeto.setConsumo(viaje.getJSONObject("vehicle").getDouble("consumo"));
				viajesujeto.setDistancia(viaje.getJSONObject("receipt").getDouble("distance"));
				String tipotransporte = viajesujeto.getTipotransporte();
				double factoremision = 0;
				switch (tipotransporte) {
				case "Etiqueta Cero":
					viajesujeto.setEcocoins(4L);
					factoremision = 0;
					break;
				case "Etiqueta Eco":
					viajesujeto.setEcocoins(3L);
					factoremision = 0.182;
					break;
				case "Etiqueta C":
					viajesujeto.setEcocoins(2L);
					factoremision = 1.671;
					break;
				case "Etiqueta B":
					viajesujeto.setEcocoins(1L);
					factoremision = 1.857;
					break;
				case "Sin Etiqueta":
					viajesujeto.setEcocoins(0L);
					factoremision = 2.295;
					break;
				}
				DecimalFormat df = new DecimalFormat("######.###");

				viajesujeto.setHuellaemision(
						df.format(viajesujeto.getConsumo() * viajesujeto.getDistancia() * factoremision));

				if (!viajesujeto.getTipotransporte().equalsIgnoreCase("Sin Etiqueta")) {
					double huellaemisionSinEtiqueta = viajesujeto.getConsumo() * viajesujeto.getDistancia() * 2.295;
					double huellaemisionDouble = viajesujeto.getConsumo() * viajesujeto.getDistancia() * factoremision;
					viajesujeto.setHuellasalvada(df.format(huellaemisionSinEtiqueta - huellaemisionDouble));
				}

				boolean eco = viajesujeto.getEcocoins() == 0 ? false : true;
				String factura = viajesujeto.getIdreceipt();
				int km = Integer.parseInt(viaje.getJSONObject("receipt").getString("distance"));
				String huellaCO2 = "futura huella de CO2";
				String bcaddress = viajesujeto.getUsuario().getBcaddress();
				try {
					viajesujeto.setTxdataviaje(
							this.guardarViajeBlockchain("Atos", bcaddress, eco, factura, km, responseHash, huellaCO2));
					viajesujeto.setTxdataecocoins(this.guardarEcoCoinsBlockchain(bcaddress, viajesujeto.getEcocoins()));
				} catch (TransactionException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (org.web3j.protocol.exceptions.TransactionException e) {
					e.printStackTrace();
				}
			}
			viajeactualizado = this.viajeService.save(viajesujeto);
		} catch (DataAccessException dae) {
			this.response.put("mensaje", "Error al actualizar el viaje");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje", "Usuario editado y actualizado en la base de datos!");
		this.response.put("usuario", viajeactualizado);
		return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.CREATED);

	}

/*** Calculos Huella ecologica *************************************************************************************************************************/

	private String calcularHuellaEmision(double consumo, double distancia, double factoremision) {
		DecimalFormat df = new DecimalFormat("######.###");
		return df.format(consumo * distancia * factoremision);
	}

	private String calcularHuellaSalvada(double consumo, double distancia, String huella) {

		double huellaemision = Double.parseDouble(huella);
		DecimalFormat df = new DecimalFormat("######.###");
		double huellaemisionSinEtiqueta = consumo * distancia * 2.295;
		return df.format(huellaemisionSinEtiqueta - huellaemision);
	}

/*** Get Proveedores ***********************************************************************************************************************************/

	@GetMapping("/viajes/proveedores")
	public List<Proveedor> listaDeProveedores() {
		return this.viajeService.findAllProveedores();
	}

/*** Guardar en BlockChain ***********************************************************************************************************************************/

	public String guardarViajeBlockchain(String empresa, String bcaddress, Boolean coche, String factura, int i,
			String proveedor, String huella)
			throws IOException, TransactionException, org.web3j.protocol.exceptions.TransactionException {

		System.out.println("Connecting to Ethereum ...");
		Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/9e71d2450c4b42ea9dd3f6d40f239ffe"));
		System.out.println("Successfuly connected to Ethereum");

		String pk = "1e0936afaeaafe8afa0016bf47aa8d6d2e6a27fd5ffe05039dcac42707d9e33d";

		Credentials credentials = Credentials.create(pk);


		String contractAddress = "0x9B5C879a67C771F55896E3460debfdD5f0C63C3b";
		System.out.println("Contrato y cuenta");

		Function function = new Function("registrarViaje", // Function name
				Arrays.asList(new org.web3j.abi.datatypes.Utf8String(empresa),
						new org.web3j.abi.datatypes.Address(bcaddress), new org.web3j.abi.datatypes.Bool(coche),
						new org.web3j.abi.datatypes.Utf8String(factura),
						new org.web3j.abi.datatypes.generated.Uint32(i),
						new org.web3j.abi.datatypes.Utf8String(proveedor),
						new org.web3j.abi.datatypes.Utf8String(huella)), // Function input parameters
				Collections.emptyList()); // Function returned parameters

		// Encode function values in transaction data format
		String txData = FunctionEncoder.encode(function);
		System.out.println("función");

		// RawTransactionManager use a wallet (credential) to create and sign
		// transaction
		TransactionManager txManager = new RawTransactionManager(web3, credentials);
		System.out.println("el buen limite");

		// Send transaction
		String txHash = txManager.sendTransaction(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT,
				contractAddress, txData, BigInteger.ZERO).getTransactionHash();
		System.out.println("transaction");

		// web3.ethSendRawTransaction(txHash);

		// Wait for transaction to be mined
		TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(web3,
				TransactionManager.DEFAULT_POLLING_FREQUENCY, TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH);
		System.out.println("todo bien hasta aquí");
		TransactionReceipt txReceipt = receiptProcessor.waitForTransactionReceipt(txHash);
		System.out.println("Viaje registrado: " + txReceipt.getTransactionHash());
		return txReceipt.getTransactionHash();

	}

	public String guardarEcoCoinsBlockchain(String bcaddress, Long ecocoins)
			throws IOException, TransactionException, org.web3j.protocol.exceptions.TransactionException {

		System.out.println("Connecting to Ethereum ...");
		Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/f39f87da1401406a93411b82454de6ae"));
		System.out.println("Successfuly connected to Ethereum");

		String pk = "8172c653ab737e71ca5535675b3ac6b0f4c7eaae5a0388bd53112da3100c07ee";

		Credentials credentials = Credentials.create(pk);

		// Contract and functions
		String contractAddress = "0x5FD2EC9c9A7e3606044F80316Cf51a7815823360";
		System.out.println("Contrato y cuenta token");

		Function function = new Function("transfer", // Function name
				Arrays.asList(
						// new org.web3j.abi.datatypes.Utf8String(bcaddress),
						new org.web3j.abi.datatypes.Address(bcaddress), 
						new Uint(BigInteger.valueOf(ecocoins * 100))), // Function input parameters
				Collections.emptyList()); // Function returned parameters
		// Encode function values in transaction data format
		String txData = FunctionEncoder.encode(function);
		System.out.println("función token");

		// RawTransactionManager use a wallet (credential) to create and sign
		// transaction
		TransactionManager txManager = new RawTransactionManager(web3, credentials);
		System.out.println("el buen limite token");

		// Send transaction
		String txHash = txManager.sendTransaction(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT,
				contractAddress, txData, BigInteger.ZERO).getTransactionHash();
		System.out.println("transaction token");

		// web3.ethSendRawTransaction(txHash);

		// Wait for transaction to be mined
		TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(web3,
				TransactionManager.DEFAULT_POLLING_FREQUENCY, TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH);
		System.out.println("todo bien hasta aquí");
		TransactionReceipt txReceipt = receiptProcessor.waitForTransactionReceipt(txHash);
		System.out.println("Ecocoins transferidos: " + txReceipt.getTransactionHash());
		return txReceipt.getTransactionHash();

	}
/****************************************************************************************************************************************************/

}