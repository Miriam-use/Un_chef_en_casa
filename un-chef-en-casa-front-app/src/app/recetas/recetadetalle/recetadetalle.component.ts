import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import Swal from "sweetalert2";
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import { Usuario } from '../../usuarios/usuario';

@Component({
  selector: 'app-recetadetalle',
  templateUrl: './recetadetalle.component.html',
  styleUrls: ['./recetadetalle.component.css']
})
export class RecetadetalleComponent implements OnInit {

  titulo: string = "Nueva Receta";
  titul: string = "Editar Receta";
  vehiculo: Receta = new Receta();
  errores: string[];

  usuario: Usuario=new Usuario();

  usuariologin:number;

  constructor(
    private vehiculoService: RecetaService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.cargarReceta();
    this.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
  }

  cargarReceta(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params["id"];
      if (id) {
        this.vehiculoService.getReceta(id).subscribe(vehiculo => {
          console.log("HOLAAAA 4");
          this.vehiculo = vehiculo;
          console.log(this.vehiculo);
        });
      }
    });
  }

  cargarReceta2(id: number): void {
    console.log("cadw" + id);
    this.vehiculoService.getReceta(id).subscribe(vehiculo => {
      this.setReceta(vehiculo);
      this.router.navigate(["/recetadetalle"]);
    });
  }

  setReceta(vehiculo: Receta) {
    console.log(vehiculo);
    this.vehiculo = vehiculo;

    console.log(this.vehiculo);
  }

  update(): void {
    this.vehiculoService.update(this.vehiculo).subscribe(json => {
      this.vehiculo = json.vehiculo;
      console.log(this.vehiculo);
      sessionStorage.setItem("recetaLongeado", JSON.stringify(this.vehiculo));
      this.router.navigate(["/receta/tabla/pg/0"]);
      Swal.fire(`¡Actualizado!`, `Tus datos han sido actualizados`, "success");
    });
  }

  create(): void {
    console.log("Create en el Component")
    this.guardarUsuario;
    console.log(this.vehiculo.idusuario);
    this.vehiculoService.create(this.vehiculo).subscribe(
      vehiculo => {
        this.router.navigate(["/receta/tabla/pg/0"]);
        Swal.fire(`Nuevo Receta`, `Receta creado`, "success");
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error("Código del error desde el backend: " + err.status);
        console.error(err.error.errors);
      }
    );
  }

  guardarUsuario(id:number):void{
    this.usuariologin = id;
    this.vehiculo.idusuario = this.usuariologin;
  }

}
