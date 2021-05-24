import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import { Usuario } from '../../usuarios/usuario';

@Component({
  selector: 'app-recetalist',
  templateUrl: './recetalist.component.html',
  styleUrls: ['./recetalist.component.css']
})
export class RecetalistComponent implements OnInit {

  vehiculos: Receta[];

  usuario: Usuario=new Usuario();

  constructor(private busquedaService:RecetaService) { }

  ngOnInit(): void {
    this.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
  }

  rellenaTablaParcial(campoBusqueda:string){
    this.vehiculos = null;
    if(campoBusqueda.length != 0){
      this.busquedaService.getIncidenciasFiltro(campoBusqueda).subscribe(
        vehiculos => this.vehiculos = vehiculos
      );
    }
  }

  eliminar(vehiculos:Receta):void{
    const swalWithBootstrapButtons = swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-3'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: `Eliminando la receta`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.busquedaService.eliminar(vehiculos.id).subscribe(
          response=>{
            this.vehiculos=this.vehiculos.filter(user=>user!==vehiculos)
            swalWithBootstrapButtons.fire(
              'Eliminado',
              `La receta fue eliminado con exíto!`,
              'success'
            )
          }
        )

      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelado',
          'Eliminación no realizada',
          'error'
        )
      }
    })
  }

}
