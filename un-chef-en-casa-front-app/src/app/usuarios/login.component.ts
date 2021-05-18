import { Component, OnInit } from '@angular/core';
import  {Usuario }from "./usuario";
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: Usuario=new Usuario();

  errores: string[];

  constructor(
    private usuarioService: UsuarioService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }

  cerrarSesion():void{
    localStorage.removeItem('usuariologueado');
  }
  loguear():void{
    this.usuarioService.getUsuarioLogin(this.usuario).subscribe(
      json=>{
        this.usuario=json.usuario;
        sessionStorage.setItem("usuariologueado",JSON.stringify(this.usuario));
        this.router.navigate(['/receta/detalles'])
        Swal.fire(`¡Bienvenido ${json.usuario.dasatos}!`,`${json.mensaje}`, 'success');
      },
      err=>{
        this.errores=err.error.errors as string[];
        console.error('Código del error desde el backend: '+err.status);
        console.error(err.error.errors);
      }
    );

    
  }

  getThisUsuarioLogueado(){
    return this.usuario;
  }


}
