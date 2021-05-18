import { Component, OnInit } from '@angular/core';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import { Router, ActivatedRoute } from "@angular/router";
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas'; // Todavía no lo usamos

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

  // tslint:disable-next-line:typedef
  downloadPDF() {
    // Extraemos el
    const DATA = document.getElementById('htmlData');
    const doc = new jsPDF('p', 'pt', 'a4');
    const options = {
      background: 'white',
      scale: 3
    };
    html2canvas(DATA, options).then((canvas) => {

      const img = canvas.toDataURL('image/PNG');

      // Add image Canvas to PDF
      const bufferX = 15;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save(`${new Date().toISOString()}_receta.pdf`);
    });
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
