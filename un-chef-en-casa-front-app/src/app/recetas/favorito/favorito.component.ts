import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../usuarios/usuario';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import { Router, ActivatedRoute } from "@angular/router";
import { Favorito } from "./favorito";
import { FavoritoService } from "./favorito.service";

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

  eliminar(incidencias:Receta):void{
  }

}
