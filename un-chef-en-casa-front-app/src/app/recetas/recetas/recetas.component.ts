import { Component, OnInit } from '@angular/core';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import swal from 'sweetalert2';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-recetas',
  templateUrl: './recetas.component.html',
  styleUrls: ['./recetas.component.css']
})
export class RecetasComponent implements OnInit {

  incidencias: Receta[];

  paginador: any;

  constructor(private incidenciaService:RecetaService) { }

  ngOnInit(): void {
    this.getAll();

    /*this.incidenciaService.paramMap.subscribe(params=>{
      let pagina:number=+params.get('pagina');
      if(!pagina){
        pagina=0;
      }
      this.incidenciaService.getIncidencia(pagina)
        .pipe(
          tap((response:any) =>{
            this.incidencias=response.content as Receta[];
            this.paginador=response;
            console.log('RecetaService: tap 3');
            (response.content as Receta[]).forEach(incidencias=>{
            console.log(incidencias.titulo);
            });
          })
        ).subscribe();
      });*/
  }

  getAll(): void{
    this.incidenciaService.getRecetas().subscribe(
      incidencias => this.incidencias = incidencias
    );
  }

  eliminar(incidencias:Receta):void{
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
        this.incidenciaService.eliminar(incidencias.id).subscribe(
          response=>{
            this.incidencias=this.incidencias.filter(user=>user!==incidencias)
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
