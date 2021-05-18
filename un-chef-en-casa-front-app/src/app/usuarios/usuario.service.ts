import {Injectable} from '@angular/core';
//import {USUARIOS} from "./usuarios.json";
import {Usuario} from './usuario';
import {of,Observable, throwError} from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable()
export class UsuarioService {

  private url: string;

  private httpHeaders=new HttpHeaders({'Content-Type':'application/json'});

  constructor(
    private http: HttpClient,
    private router: Router
  ){}
/*
  getUsuarios(): Observable<Usuario[]>{
    this.url="http://localhost:8082/ecomove/v0.1/usuarios";
    //return of(USUARIOS);
    //return this.http.get<Usuario[]>(this.url);
    //return this.http.get(this.url).pipe(map(function (response){return response as Usuario[]}));
    return this.http.get(this.url).pipe(
      tap(response=>{
        let usuarios=response as Usuario[];
        console.log('UsuarioService: tap 1')
        usuarios.forEach(usuario=>{
          console.log(usuario.correo);
        }
        )
      }),
      map(response => {
        let usuarios=response as Usuario[];
        return usuarios.map(usuario=>{
          usuario.correo=usuario.correo.toUpperCase();
          return usuario;
        });
      }),
      tap(response=>{
        console.log('UsuarioService: tap 2')
        response.forEach(usuario=>{
          console.log(usuario.correo);
        }
        )
      })
    );
  }*/

  getUsuarios(pagina:number): Observable<any[]>{
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
  }

  getUsuario(id): Observable<Usuario>{
    this.url="http://localhost:8082/ecomove/v0.1/usuarios";
    return this.http.get<Usuario>(`${this.url}/${id}`).pipe(
      catchError(e=>{
        console.log("HOLAAAA 5");
        console.error(e.error.mensaje);
        this.router.navigate(['/usuarios'])
        Swal.fire('Error al editar',e.error.mensaje, 'error')
        return throwError(e);
      })
    );
  }

  getUsuarioLogin(usuario: Usuario): Observable<any>{
    this.url="http://localhost:8082/ecomove/v0.1/usuarios/login";
    let params = new HttpParams().set("dasatos",usuario.dasatos).set("contrasena",usuario.contrasena);
    return this.http.get<Usuario>(this.url,{headers:this.httpHeaders, params}).pipe(
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

  update(usuario: Usuario):Observable<any>{///usuario/{id}")
    this.url="http://localhost:8082/ecomove/v0.1/usuario";
    return this.http.put<any>(`${this.url}/${usuario.id}`, usuario,{headers:this.httpHeaders}).pipe(
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

  create(usuario: Usuario):Observable<Usuario>{
    this.url="http://localhost:8082/ecomove/v0.1/usuario";
    return this.http.post(this.url, usuario,{headers:this.httpHeaders}).pipe(
      map((response:any)=>response.usuario as Usuario),
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

  delete(id: number): Observable<Usuario>{
    this.url="http://localhost:8082/ecomove/v0.1/usuario";
    return this.http.delete<Usuario>(`${this.url}/${id}`,{headers:this.httpHeaders}).pipe(
      catchError(e=>{
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error')
        return throwError(e);
      })
    );
  }
}
