import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario'
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { UsuarioDetalle } from './usuariodetalle';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  usuario: Usuario=new Usuario();
  usuariodetalle: UsuarioDetalle=new UsuarioDetalle();
  titulo: string="Editar Datos";
  titulos: string="Crear Usuario";
  errores: string[];

  detalle:string;

  constructor(
    private usuarioService:UsuarioService,
    private router:Router,
    private activatedRoute: ActivatedRoute
  ){}

  ngOnInit(){
    //this.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
    this.cargarUsuario();
  }

  cargarUsuario():void{
    this.activatedRoute.params.subscribe(params=>{
      let id=params['id'];
      if(id){
        this.usuarioService.getUsuario(id).subscribe((usuario)=>{
          console.log("HOLAAAA 4");
          this.usuario=usuario;
          this.usuariodetalle=usuario.usuariodetalle;
          this.usuario.usuariodetalle=this.usuariodetalle;
          console.log(this.usuario);
        });
      }
    })
  }

  cargarUsuario2(id:number):void{
    console.log("cadw"+id);
    this.usuarioService.getUsuario(id).subscribe((usuario)=>{
      this.setUsuario(usuario);
      this.router.navigate(['/usuarios/form']);
    });
  }

  setUsuario(usuario:Usuario){
    console.log(usuario);
  //  this.usuariodetalle=usuario.usuariodetalle;
    this.usuario=usuario;

    console.log(this.usuario);
  }

  update():void{
    console.log("eyy");
    console.log(this.usuario.usuariodetalle);
    console.log(this.usuariodetalle);
    this.usuario.usuariodetalle=this.usuariodetalle;
    this.usuarioService.update(this.usuario).subscribe(
      json=>{
        this.usuario=json.usuario;
        console.log("eyy2");
        console.log(this.usuario.usuariodetalle);
        sessionStorage.setItem("usuariologueado",JSON.stringify(this.usuario));
        this.router.navigate(['/usuarios/detalle']);
        Swal.fire(`??Actualizado!`,`Tus datos han sido actualizados`, 'success');
        //Swal.fire(`Usuario ${json.usuario.dasatos} editado`,`${json.mensaje}`, 'success');
      },
      err=>{
        this.errores=err.error.errors as string[];
        console.error('C??digo del error desde el backend: '+err.status);
        console.error(err.error.errors);
      }
    )
  }

  create():void{
    this.usuarioService.create(this.usuario).subscribe(
      usuario=>{
        this.router.navigate(['/usuarios/login']);
        Swal.fire(`Nuevo usuario`,`Usuario ${usuario.dasatos} creado`, 'success')
      },
      err=>{
        this.errores=err.error.errors as string[];
        console.error('C??digo del error desde el backend: '+err.status);
        console.error(err.error.errors);
      }
    )
  }

}
