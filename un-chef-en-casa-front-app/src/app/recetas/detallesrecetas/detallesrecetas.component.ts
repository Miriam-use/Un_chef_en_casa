import { Component, OnInit } from '@angular/core';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-detallesrecetas',
  templateUrl: './detallesrecetas.component.html',
  styleUrls: ['./detallesrecetas.component.css']
})
export class DetallesrecetasComponent implements OnInit {

  vehiculo: Receta = new Receta();

  constructor(
    private vehiculoService: RecetaService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.cargarReceta();
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

}
