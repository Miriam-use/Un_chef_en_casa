package api.cocinacasa.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.cocinacasa.back.entity.Viaje;
import api.cocinacasa.back.service.DashboardService;

@RestController
@RequestMapping("/ecomove/v0.1/dashboard")
@CrossOrigin(origins= {"*"})
public class DashboardController {
		
	@Autowired
	private DashboardService dashboardService;
	
	Map<String,Object> response=new HashMap<>();

/**Get Datos necesarios para el Dashboard*****************************************************************************************************************/
		
	// Datos necesarios en base al usuario logueado		
	@GetMapping("/{usuarioid}")
	public ResponseEntity<?> getDatos(@PathVariable Long usuarioid){				
		List<Viaje>viajes=this.dashboardService.getViajesPorUsuarioCompletados(usuarioid);		
		int totalviajes=viajes.size();	
		Map<String,String>datosdashboard=this.dashboardService.getDatosDashboard(usuarioid);	
		System.out.println(datosdashboard);	
		// huella de carbono total	
		double huellatotal=0;	 
		for (Viaje viaje:viajes){	
			double factordeemision=0;		 
		 	switch(viaje.getTipotransporte()){
		 		case "Etiqueta Cero":
		 			factordeemision=0;
		 		break;	 		
		 		case "Etiqueta Eco":
		 			factordeemision=0.182;
		 		break;		 		
		 		case "Etiqueta C":
		 			factordeemision=1.671;
		 		break;		 		
		 		case "Etiqueta B":
		 			factordeemision=1.857;
		 		break;		 		
		 		case "Sin Etiqueta":
		 			factordeemision=2.295;
		 		break; 	
		 	}		 	
			double huelladelviaje= viaje.getConsumo() * viaje.getDistancia() * factordeemision;
			huellatotal= huellatotal+huelladelviaje;
		}		 			
		// huella de carbono salvada		
		List<Viaje> viajesecologicos=this.dashboardService.getViajesEcologicos(usuarioid);		 
		double huellasalvada=0;		 	 		 	 
		for (Viaje viaje:viajesecologicos){
		 	double factordeemision=0;		 
		 	switch(viaje.getTipotransporte()){
		 		case "Etiqueta Cero":
		 			factordeemision=0;
		 		break;	 		
		 		case "Etiqueta Eco":
		 			factordeemision=0.182;
		 		break;		 		
		 		case "Etiqueta C":
		 			factordeemision=1.671;
		 		break;		 		
		 		case "Etiqueta B":
		 			factordeemision=1.857;
		 		break;		 		
		 		case "Sin Etiqueta":
		 			factordeemision=2.295;
		 		break; 	
		 	}		 	
		 	double huelladelviajeeco= viaje.getConsumo() * viaje.getDistancia() * factordeemision;		 	
		 	double huellaifviajenoeco= 0.024 *  viaje.getDistancia() * 2.295;		 	
		 	huellasalvada= huellasalvada+(huellaifviajenoeco-huelladelviajeeco);	 	
		 }	
		this.response.put("viajessin",datosdashboard.get("viajessin"));
		this.response.put("ecocoins",datosdashboard.get("ecocoins"));
		this.response.put("viajescero",datosdashboard.get("viajescero"));
		this.response.put("totalviajesnoeco",datosdashboard.get("totalviajesnoeco"));
		this.response.put("totalviajeseco",datosdashboard.get("totalviajeseco"));
		this.response.put("viajesb",datosdashboard.get("viajesb"));
		this.response.put("viajeseco",datosdashboard.get("viajeseco"));
		this.response.put("viajesc",datosdashboard.get("viajesc"));
		this.response.put("huellatotal",huellatotal);
		this.response.put("huellasalvada",huellasalvada);
		this.response.put("totalviajes",totalviajes);
		this.response.put("kmeco",datosdashboard.get("kmeco"));
		if(datosdashboard.get("kmnoeco")==null)
			this.response.put("kmnoeco",0);
		else
			this.response.put("kmnoeco",datosdashboard.get("kmnoeco"));		
		return new ResponseEntity<Map<String,Object>>(this.response,HttpStatus.OK);
	}
}
	
/*
	  
  Etiqueta Cero:
  	-eléctricos
  	-electricos de autonomia extendida
  	-hibridos enchufables con autonomia igual o mayor a 40km 
  	-vehículos de pila de combustible de hidrógeno
  	
  Etiqueta Eco:
  	-híbridos 
  	-híbridos enchufables con autonomía inferior a 40km
  	-impulsados por gas natural (GNC y GNL) o por gas licuado del petróleo (GLP)
  
 Etiqueta C: 
 	-turismos y furgonetas ligeras de gasolina matriculadas a partir de enero de 2006 y diésel a partir de 2014
 Etiqueta B:
  	-turismos y furgonetas ligeras de gasolina matriculadas a partir de enero de 2000 y de diésel a partir de enero de 2006  	    
	  
*/