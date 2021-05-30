import { Component, OnInit } from '@angular/core';
import { Receta } from "../receta";
import { RecetaService } from "../receta.service";
import { Router, ActivatedRoute } from "@angular/router";
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { Usuario } from '../../usuarios/usuario';
import { FavoritoService } from "../favorito/favorito.service";
import { Favorito } from "../favorito/favorito";
import Swal from "sweetalert2";

@Component({
  selector: 'app-detallesrecetas',
  templateUrl: './detallesrecetas.component.html',
  styleUrls: ['./detallesrecetas.component.css']
})
export class DetallesrecetasComponent implements OnInit {

  vehiculo: Receta = new Receta();

  usuario: Usuario=new Usuario();

  favorito: Favorito=new Favorito();

  favoritos: Favorito[];

  usuariologin:number;
  recetaactual:string;

  errores: string[];

  esta:boolean=false;

  constructor(
    private vehiculoService: RecetaService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private favoritoService: FavoritoService
  ) { }

  ngOnInit(): void {
    this.cargarReceta();
    this.getAllFavorito();
    this.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
  }

  compro(){
    this.esta=true;
  }

  comprovar(id:number, iid:string){
    console.log(id, iid);
  }

  cargarUsuario():void{
    console.log(this.usuario.id);
    console.log(this.vehiculo.id);
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

  favoritoanadi(): void {
    console.log("Create en el Component")
    this.guardarUsuario;
    this.guardarReceta;
    console.log(this.favorito.idreceta, this.favorito.idusuario);
    this.favoritoService.create(this.favorito).subscribe(
      favorito => {
        this.router.navigate(["/receta/tabla/pg/0"]);
        Swal.fire(`Receta Guardada`, `Receta Favorito`, "success");
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
    this.favorito.idusuario = this.usuariologin;
  }

  guardarReceta(id:string):void{
    this.recetaactual = id;
    this.favorito.idreceta = this.recetaactual;
  }

  getAllFavorito(): void{
    this.favoritoService.getFavoritos().subscribe(
      favoritos => this.favoritos = favoritos
    );
  }

  eliminar(favoritos:Favorito):void{
    const swalWithBootstrapButtons = Swal.mixin({
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
        result.dismiss === Swal.DismissReason.cancel
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
