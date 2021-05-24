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

  private urlEndPoint:string = 'http://localhost:8082/ecomove/v0.1/';
  private urlDefinitiva:string = this.urlEndPoint;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  getFavoritos(): Observable<any>{
    return this.http.get<Favorito>(`${this.urlEndPoint}/favorito/all`).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        //swal.fire('Error, no se ha encontrado esta busqueda',e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }
}
