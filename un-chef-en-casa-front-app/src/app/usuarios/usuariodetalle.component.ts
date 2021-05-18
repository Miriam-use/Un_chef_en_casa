import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormComponent } from './form.component';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { UsuarioDetalle } from './usuariodetalle';

@Component({
  selector: 'app-usuariodetalle',
  templateUrl: './usuariodetalle.component.html',
  styleUrls: ['./usuariodetalle.component.css']
})
export class UsuariodetalleComponent implements OnInit {

  constructor(
    private usuarioService:UsuarioService,
    private router:Router,
    private formcomponent: FormComponent
  ) { }

  usuariodetalle: UsuarioDetalle=new UsuarioDetalle();

  usuario: Usuario=new Usuario();



  ngOnInit(): void {
    this.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
  }

  cargarUsuario(id:number):void{
    console.log(id);
    // this.usuarioService.getUsuario(id).subscribe((usuario)=>this.usuario=usuario)
    // this.router.navigate(['/usuarios/form']);

    this.formcomponent.cargarUsuario2(id);
  }

}
