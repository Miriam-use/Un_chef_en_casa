import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
//import { ContractsService } from '../contracts/contracts.service';
import { Proveedor } from '../proveedores/proveedor';
import { Usuario } from '../usuarios/usuario';
import { Viaje } from './viaje';
import { ViajeService } from './viaje.service';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-viajes',
  templateUrl: './viajes.component.html',
  styleUrls: ['./viajes.component.css']
})
export class ViajesComponent implements OnInit {

  //mostrar: boolean=true;
  viajes: Viaje[];
  viaje:Viaje;
  detalles:boolean;
  usuario:Usuario=new Usuario();
  proveedores: Proveedor[];
  estado:string;
  fecha:string;
  proveedor:Proveedor=new Proveedor();
  origen:string;
  destino:string;
  paginador:any;
  leng:number;

  constructor(
    private viajeService: ViajeService,
    private router:Router,
    private activatedRoute:ActivatedRoute
//  private contractsService:ContractsService
  ) { }

  ngOnInit(){
    this.activatedRoute.paramMap.subscribe(params =>{
      let pagina:number = +params.get('pagina');
      if(!pagina)
        pagina=0;
      this.viajeService.getViajesPaginados(pagina).subscribe(response => {
        this.viajes=response.content as Viaje[];
        this.paginador = response;
      });
    });
    this.viajeService.getProveedores2(this);
//  this.viajeService.getViajesPaginados(0).subscribe(response => this.viajes=response.content as Viaje[]);
//  this.viajeService.getViajes().subscribe(viajes => this.viajes=viajes);
//  this.contractsService.init();
  }

  setProveedores(proveedores:Proveedor[]){
    this.proveedores=proveedores;
  }

  setDetalles(): void{
    //this.mostrar=(this.mostrar==true)?false:true;
    this.detalles=(this.detalles==true)?false:true;
  }

  limpiar(){
    this.estado=undefined;
    this.fecha=undefined;
    this.proveedor=undefined;
    this.origen=undefined;
    this.destino=undefined;
  }

  buscarFiltradosBack(){
    if(!this.proveedor)
      this.proveedor=new Proveedor();
    this.viajeService.getViajesFiltrados(this.estado,this.fecha,this.proveedor.id,this.origen,this.destino).subscribe(viajes => this.viajes=viajes);
  }

  buscarFiltradosPaginados(){
    this.activatedRoute.paramMap.subscribe(params =>{
      let pagina:number = +params.get('pagina');
      if(!pagina)
        pagina=0;
      if(!this.proveedor)
        this.proveedor=new Proveedor();
      this.viajeService.getViajesFiltradosPaginados(pagina,this.estado,this.fecha,this.proveedor.id,this.origen,this.destino)
      .subscribe(response=>{
        this.viajes=response.content as Viaje[];
        console.log(this.viajes.length);
        console.log(this.leng);
        this.leng=this.viajes.length;
        console.log(this.leng);
        if(this.leng==0)
          console.log("leng vale 0");
        else
          console.log("leng vale 1")
        this.paginador=response;
      });
    });
  }

  buscarFiltrados(){// antiguo
    this.viajeService.getViajes()
    .subscribe(viajes => {
      console.log(this.proveedor);
      if(this.estado && this.estado=="completed")
        viajes=viajes.filter(viaje=>viaje.status==this.estado);
      if(this.estado && this.estado=="incompleted")
        viajes=viajes.filter(viaje=>viaje.status!="completed");
      if(this.fecha)
        viajes=viajes.filter(viaje=>viaje.fechaida==this.fecha);
      if(this.proveedor && typeof(this.proveedor)!="string")
        viajes=viajes.filter(viaje=>viaje.proveedor.nombre==this.proveedor.nombre);
      if(this.origen)
        viajes=viajes.filter(viaje=>viaje.origen==this.origen);
      if(this.destino)
        viajes=viajes.filter(viaje=>viaje.destino==this.destino);
      this.viajes=viajes;
    });
  }

  updateStatus(viaje: Viaje):void{
    console.log(viaje);
    console.log(this.estado,this.fecha,this.proveedor,this.origen,this.destino);
    this.viajeService.updateStatusUber(viaje).subscribe(response=>{
      console.log(response);
      this.viajeService.updateStatus(response).subscribe( _response=> {
        this.viajeService.getViajes().subscribe(viajes => {
          this.viajes=viajes;
          this.buscarFiltradosPaginados();
          //this.router.navigate(['/viajes']);
        });
      });
    });
  }

  detallesViaje(viaje:Viaje):void{
    console.log('entrando en detallesViaje');
    this.viajeService.getdetallesViaje(viaje.id).subscribe(response=>{
    this.viaje=response;
    this.detalles=true;//this.mostrar=false;
    console.log('detalles del viaje');
    console.log(this.viaje);console.log(this.detalles);
    });
  }

  detallesViaje2(viaje:Viaje):void{
    console.log('entrando en detallesViaje');
    this.activatedRoute.paramMap.subscribe(params =>{
      let pagina:number = +params.get('pagina');
      if(!pagina)
        pagina=0;
      console.log('pagina: '+pagina);
      this.viajeService.getdetallesViaje(viaje.id).subscribe(response=>{
        this.viaje=response;
        this.detalles=true;//this.mostrar=false;
        console.log('detalles del viaje');
        console.log(this.viaje);console.log(this.detalles);
        //this.router.navigate([`/viajes/pg/${pagina}`]);
      });
    });
  }

}
