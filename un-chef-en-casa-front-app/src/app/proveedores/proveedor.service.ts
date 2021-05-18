import {Injectable} from '@angular/core';
import {Proveedor} from './proveedor';
import {of,Observable, throwError} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable()
export class ProveedorService {

  private url: string;

  private httpHeaders=new HttpHeaders({'Content-Type':'application/json'});

  constructor(
    private http: HttpClient,
    private router: Router
  ){}

  getProveedores(): Observable<Proveedor[]>{
    this.url="http://localhost:8082/ecomove/v0.1/viajes/proveedores";
    let listaProveedores:Observable<Proveedor[]>;
    listaProveedores=this.http.get<Proveedor[]>(this.url);
    return listaProveedores;
  }



  /*getUsuarios(pagina:number): Observable<any[]>{
    this.url="http://localhost:8082/ecomove/v0.1/usuarios/pg/";
    return this.http.get(this.url+pagina).pipe(
      tap((response:any)=>{
        console.log('UsuarioService: tap 1');
        (response.content as Usuario[]).forEach(usuario=>{
          console.log(usuario.correo);
        });
      }),
      map((response:any) => {
        (response.content as Usuario[]).map(usuario=>{
          usuario.correo=usuario.correo.toUpperCase();
          return usuario;
        });
        return response;
      }),
      tap(response=>{
        console.log('UsuarioService: tap 2');
        (response.content as Usuario[]).forEach(usuario=>{
          console.log(usuario.correo);
        }
        )
      })
    );
  }*/

  /*getViajes(): Observable<Viaje[]>{
    this.url="http://localhost:8082/ecomove/v0.1/viajes";
    //return of(USUARIOS);
    //return this.http.get<Viaje[]>(this.url);
    //return this.http.get(this.url).pipe(map(function (response){return response as Usuario[]}));
    return this.http.get(this.url).pipe(
      map(response => {
        let viajes=response as Viaje[];
        return viajes.map(viaje=>{
          viaje.status=viaje.status.toUpperCase();
          //viaje.fecha=formatDate(viaje.fecha,'dd-MM-yyyy','es');
          let datePipe=new DatePipe('es');
          viaje.fechaida=datePipe.transform(viaje.fechaida,'fullDate');//viaje.fechaida=datePipe.transform(viaje.fechaida,'EEEE, dd MMMM yyyy');
          return viaje;
        });
      })
    );
  }*/

}
