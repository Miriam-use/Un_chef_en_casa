import { UsuarioDetalle } from './usuariodetalle';

export class Usuario {
  /*constructor(  id:number, correo: string, contrasena: string,nombre: string) {
    this.id=id;
    this.correo=correo;
    this.contrasena=contrasena;
    this.nombre=nombre;
  }*/
  id:number;
  dasatos: string;
  correo: string;
  contrasena: string;
  usuariodetalle:UsuarioDetalle;
  createAt:string;
  bcaddress:string;
}
