import { Component, OnInit } from '@angular/core';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";

@Component({
  selector: 'app-recetas',
  templateUrl: './recetas.component.html',
  styleUrls: ['./recetas.component.css']
})
export class RecetasComponent implements OnInit {

  incidencias: Receta[];

  constructor(private incidenciaService:RecetaService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void{
    this.incidenciaService.getRecetas().subscribe(
      incidencias => this.incidencias = incidencias
    );
  }

}
