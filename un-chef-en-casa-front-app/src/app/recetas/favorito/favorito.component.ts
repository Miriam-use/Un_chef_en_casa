import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../usuarios/usuario';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import { Router, ActivatedRoute } from "@angular/router";
import { Favorito } from "./favorito";
import { FavoritoService } from "./favorito.service";
import swal from 'sweetalert2';

@Component({
  selector: 'app-favorito',
  templateUrl: './favorito.component.html',
  styleUrls: ['./favorito.component.css']
})
export class FavoritoComponent implements OnInit {

  usuario: Usuario=new Usuario();

  favorito: Favorito=new Favorito();
  favoritos: Favorito[];

  receta: Receta[];

  

  constructor(private recetaService:RecetaService, private activatedRoute: ActivatedRoute, private favoritoService:FavoritoService) { }

  ngOnInit(): void {
    this.getAll();
    this.getAllFavorito();
    this.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
  }

  getAll(): void{
    this.recetaService.getRecetas().subscribe(
      receta => this.receta = receta
    );
  }

  getAllFavorito(): void{
    this.favoritoService.getFavoritos().subscribe(
      favoritos => this.favoritos = favoritos
    );
  }

  eliminar(favoritos:Favorito):void{
    const swalWithBootstrapButtons = swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-3'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: `Eliminando la receta de favorito`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.favoritoService.eliminar(favoritos.id).subscribe(
          response=>{
            this.favoritos=this.favoritos.filter(user=>user!==favoritos)
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
