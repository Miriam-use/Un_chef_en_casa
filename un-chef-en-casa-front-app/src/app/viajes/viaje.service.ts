import { Injectable } from '@angular/core';
import { Viaje } from './viaje';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { DatePipe } from '@angular/common'
import { Proveedor } from '../proveedores/proveedor';
import { ViajeUber } from './viajeUber';
import { FormnuevoviajeuberComponent } from './formnuevoviajeuber.component';
import { ViajesComponent } from './viajes.component';

@Injectable()
export class ViajeService {

  private url: string="http://localhost:8081/uber/v1.2/requests/current";

  private httpHeaders=new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http: HttpClient) { }

  getProveedores(ob:FormnuevoviajeuberComponent){
    this.url="http://localhost:8082/ecomove/v0.1/viajes/proveedores";
    return this.http.get<Proveedor[]>(this.url,{headers:this.httpHeaders}).subscribe(response=>ob.setProveedores(response));
  }

  getProveedores2(ob:ViajesComponent){
    this.url="http://localhost:8082/ecomove/v0.1/viajes/proveedores";
    return this.http.get<Proveedor[]>(this.url,{headers:this.httpHeaders}).subscribe(response=>ob.setProveedores(response));
  }

  getViajes(): Observable<Viaje[]>{
    this.url="http://localhost:8082/ecomove/v0.1/viajesusuario";
    let id=JSON.parse(sessionStorage.getItem("usuariologueado")).id;
    return this.http.get(`${this.url}/${id}`,{headers:this.httpHeaders}).pipe( //`${this.url}/${id}`
      map(response => {
        let viajes=response as Viaje[];
        return viajes.map(viaje=>{
          let datePipe=new DatePipe('es');
          //viaje.fechaida=datePipe.transform(viaje.fechaida,'fullDate');//viaje.fechaida=datePipe.transform(viaje.fechaida,'EEEE, dd MMMM yyyy'); viaje.status=viaje.status.toUpperCase();viaje.fecha=formatDate(viaje.fecha,'dd-MM-yyyy','es');
          return viaje;
        });
      })
    );
  }

  getViajesPaginados(pagina: number):Observable<any>{
    this.url="http://localhost:8082/ecomove/v0.1/viajesusuariopag";
    let id=JSON.parse(sessionStorage.getItem("usuariologueado")).id;
    let params=new HttpParams().set("id",id).set("pagina",pagina.toString());
    return this.http.get(this.url,{headers:this.httpHeaders, params:params}).pipe( //`${this.url}/${id}`
      map((response:any) => {
        (response.content as Viaje[]).map(viaje=>{
          let datePipe=new DatePipe('es');
          //viaje.fechaida=datePipe.transform(viaje.fechaida,'fullDate');//viaje.fechaida=datePipe.transform(viaje.fechaida,'EEEE, dd MMMM yyyy'); viaje.status=viaje.status.toUpperCase();viaje.fecha=formatDate(viaje.fecha,'dd-MM-yyyy','es');
          return viaje;
        });
        return response;
      })
    );
  }

  getViajesFiltrados(estado,fecha,proveedor,origen,destino):Observable<Viaje[]>{
    this.url="http://localhost:8082/ecomove/v0.1/viajesusuario";
    let id=JSON.parse(sessionStorage.getItem("usuariologueado")).id;
    let params=new HttpParams().set("id",id).set("status",estado).set("fechaida",fecha).set("proveedor",proveedor).set("origen",origen).set("destino",destino);
    return this.http.get<Viaje[]>(this.url,{headers:this.httpHeaders,params:params});

  }

  getViajesFiltradosPaginados(pagina: number,estado,fecha,proveedor,origen,destino):Observable<any>{
    this.url="http://localhost:8082/ecomove/v0.1/viajesusuariopag3";
    let id=JSON.parse(sessionStorage.getItem("usuariologueado")).id;
    let params=new HttpParams().set("id",id).set("pagina",pagina.toString()).set("status",estado).set("fechaida",fecha).set("proveedor",proveedor).set("origen",origen).set("destino",destino);
    return this.http.get(this.url,{headers:this.httpHeaders, params:params}).pipe(
      map((response:any) => {
        (response.content as Viaje[]).map(viaje=>{
          return viaje;
        });
        return response;
      })
    );
  }

  // getViajesPaginado(): Observable<Viaje[]>{
  //   this.url="http://localhost:8082/ecomove/v0.1/viajesusuario";
  //   let id=JSON.parse(sessionStorage.getItem("usuariologueado")).id;
  //   let params=new HttpParams().set("id",id).set("pagina", "2"); //Create new HttpParams
  //   return this.http.get(this.url,{headers:this.httpHeaders,params:params}).pipe( //`${this.url}/${id}`
  //     map(response => {
  //       let viajes=response as Viaje[];
  //       return viajes.map(viaje=>{
  //         let datePipe=new DatePipe('es');
  //         //viaje.fechaida=datePipe.transform(viaje.fechaida,'fullDate');//viaje.fechaida=datePipe.transform(viaje.fechaida,'EEEE, dd MMMM yyyy'); viaje.status=viaje.status.toUpperCase();viaje.fecha=formatDate(viaje.fecha,'dd-MM-yyyy','es');
  //         return viaje;
  //       });
  //     })
  //   );
  // }

  public getdetallesViaje(id):Observable<Viaje>{
    this.url="http://localhost:8082/ecomove/v0.1/viajes";
    return this.http.get<Viaje>(`${this.url}/${id}`,{headers:this.httpHeaders});
  }
  create(viaje: Viaje):Observable<Viaje>{
    this.url="http://localhost:8082/ecomove/v0.1/viaje";
    return this.http.post<Viaje>(this.url, viaje,{headers:this.httpHeaders});
  }

  createViajeUber(viajeUber: ViajeUber):Observable<any>{
    console.log(viajeUber.fecharecogida);
    this.url="http://localhost:8081/uber/v1.2/requests";
    return this.http.post<any>(this.url, viajeUber,{headers:this.httpHeaders});
  }

  public updateStatusUber(viaje: Viaje):Observable<any>{
      this.url="http://localhost:8081/uber/v1.2/requests/current/status";
      console.log("entrando en updateStatusUber");
      let params=new HttpParams().set("tipotransporte",viaje.tipotransporte);
      console.log(params);
      return this.http.patch<any>(this.url, viaje,{headers:this.httpHeaders,params:params});
  }
  public updateStatus(respuesta:any):Observable<any>{
      this.url="http://localhost:8082/ecomove/v0.1/viaje";
      return this.http.patch<any>(this.url, respuesta,{headers:this.httpHeaders});
  }

}