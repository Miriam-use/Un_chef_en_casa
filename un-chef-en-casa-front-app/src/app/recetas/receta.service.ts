import { Injectable } from '@angular/core';
import {of,Observable, throwError} from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Receta } from './receta';

@Injectable({
  providedIn: 'root'
})
export class RecetaService {

  private url: string;

  private httpHeaders=new HttpHeaders({'Content-Type':'application/json'});

  private urlEndPoint:string = 'http://localhost:8082/ecomove/v0.1/';
  private urlDefinitiva:string = this.urlEndPoint;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  create(receta: Receta):Observable<Receta>{
    this.url="http://localhost:8082/ecomove/v0.1/receta";
    return this.http.post(this.url, receta,{headers:this.httpHeaders}).pipe(
      map((response:any)=>response.usuario as Receta),
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

  getReceta(id): Observable<Receta>{
    this.url="http://localhost:8082/ecomove/v0.1/receta-id";
    return this.http.get<Receta>(`${this.url}/${id}`).pipe(
      catchError(e=>{
        console.log("HOLAAAA 5");
        console.error(e.error.mensaje);
        this.router.navigate(['/receta'])
        Swal.fire('Error al editar',e.error.mensaje, 'error')
        return throwError(e);
      })
    );
  }

  update(receta: Receta):Observable<any>{///coche/{id}")
    this.url="http://localhost:8082/ecomove/v0.1/recetaupdate";
    return this.http.put<any>(`${this.url}/${receta.id}`, receta,{headers:this.httpHeaders}).pipe(
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

  eliminar(id: String): Observable<Receta>{
    this.url="http://localhost:8082/ecomove/v0.1/receta/baja";
    return this.http.delete<Receta>(`${this.url}/${id}`,{headers:this.httpHeaders}).pipe(
      catchError(e=>{
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error')
        return throwError(e);
      })
    );
  }
  
  getRecetaFiltro(dato:string): Observable<any>{
    return this.http.get<Receta>(`${this.urlEndPoint}/recetatitulo/${dato}`).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        Swal.fire('Error, no se ha encontrado esta busqueda',e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

  getRecetas(): Observable<any>{
    return this.http.get<Receta>(`${this.urlEndPoint}/receta/all`).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        //swal.fire('Error, no se ha encontrado esta busqueda',e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

  getIncidenciasFiltro(dato:string): Observable<any>{
    return this.http.get<Receta>(`${this.urlEndPoint}/receta/filtro/${dato}`).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        Swal.fire('Error, no se ha encontrado esta busqueda',e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

}
