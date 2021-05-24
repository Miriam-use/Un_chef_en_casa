import { Injectable } from '@angular/core';
import {of,Observable, throwError} from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Favorito } from './favorito';

@Injectable({
  providedIn: 'root'
})
export class FavoritoService {

  private url: string;

  private httpHeaders=new HttpHeaders({'Content-Type':'application/json'});

  private urlEndPoint:string = 'http://localhost:8082/ecomove/v0.1/';
  private urlDefinitiva:string = this.urlEndPoint;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  create(receta: Favorito):Observable<Favorito>{
    this.url="http://localhost:8082/ecomove/v0.1/favorito";
    return this.http.post(this.url, receta,{headers:this.httpHeaders}).pipe(
      map((response:any)=>response.usuario as Favorito),
      catchError(e=>{
        if(e.status==400){
          return throwError(e);
        }
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error')
        return throwError(e);
      })
    );
  }

  getFavoritos(): Observable<any>{
    return this.http.get<Favorito>(`${this.urlEndPoint}/favorito/all`).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        //swal.fire('Error, no se ha encontrado esta busqueda',e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

  eliminar(id: String): Observable<Favorito>{
    this.url="http://localhost:8082/ecomove/v0.1/favorito/baja";
    return this.http.delete<Favorito>(`${this.url}/${id}`,{headers:this.httpHeaders}).pipe(
      catchError(e=>{
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error')
        return throwError(e);
      })
    );
  }
}
