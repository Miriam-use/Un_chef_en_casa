import { Component, OnInit, ViewChild } from '@angular/core';
import { Viaje } from './viaje'
import { ViajeService } from './viaje.service';
import { Router } from '@angular/router';
import { MatDatepicker } from '@angular/material/datepicker';
import { Proveedor } from '../proveedores/proveedor';
import { ViajeUber } from './viajeUber';
import { GooglePlaceDirective } from 'ngx-google-places-autocomplete';
import { Address } from 'ngx-google-places-autocomplete/objects/address';

@Component({
  selector: 'app-formnuevoviajeuber',
  templateUrl: './formnuevoviajeuber.component.html',
  styleUrls: ['./formnuevoviajeuber.component.css']
})
export class FormnuevoviajeuberComponent implements OnInit {

  @ViewChild(MatDatepicker) datepicker: MatDatepicker<Date>;
  @ViewChild("placesRef") placesRef : GooglePlaceDirective;

  viaje: Viaje=new Viaje();
  proveedores: Proveedor[];
  viajeUber: ViajeUber=new ViajeUber();

  options={
    types:[],
    componentRestrictions: {country: 'ES'}
  }

  addressOrigen:string;
  addressDestino:string;
  latitudeOrigen:number;
  longitudeOrigen:number;
  latitudeDestino:number;
  longitudeDestino:number;

  constructor(private viajeService:ViajeService, private router:Router) { }

  ngOnInit(){
    this.viajeService.getProveedores(this);
  }

  handleAddressChangeOrigen(address: Address) {
    this.addressOrigen=address.formatted_address;
    this.latitudeOrigen=address.geometry.location.lat();
    this.longitudeOrigen=address.geometry.location.lng();
  }

  handleAddressChangeDestino(address: Address) {
    this.addressDestino=address.formatted_address;
    this.latitudeDestino=address.geometry.location.lat();
    this.longitudeDestino=address.geometry.location.lng();
  }

  setProveedores(proveedores:Proveedor[]){
    this.proveedores=proveedores;
    this.viaje.proveedor=this.proveedores.find(proveedor=>proveedor.nombre=="uber");
  }

  compararProveedor(obj1: Proveedor, obj2: Proveedor):boolean{
    if(obj1===undefined && obj2===undefined){
      return true;
    }
    return obj1===null || obj2===null || obj1===undefined || obj2===undefined?false:obj1.id===obj2.id;
  }

  public createViajeUber():void{
    this.viajeUber.addresspickup=this.addressOrigen;
    this.viajeUber.addressdestiny=this.addressDestino;
    this.viajeUber.latitudep=this.latitudeOrigen;
    this.viajeUber.longitudep=this.longitudeOrigen;
    this.viajeUber.latituded=this.latitudeDestino;
    this.viajeUber.longituded=this.longitudeDestino;
    console.log(this.viajeUber);
    this.viajeUber.usuarioatos=JSON.parse(sessionStorage.getItem("usuariologueado")).dasatos;
    this.viajeService.createViajeUber(this.viajeUber).subscribe(response=>{
      console.log(response);
      this.viaje.rideid=response.ride_table_id;
      this.viaje.requestid=response.request_id;
      this.viaje.fecha=response.fecharide;
      this.viaje.fechaida=response.fecharecogida;
      this.viaje.horaida=response.horarecogida;
      this.viaje.status=response.status;
      this.viaje.origen=this.viajeUber.addresspickup;
      this.viaje.destino=this.viajeUber.addressdestiny;
      this.viaje.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
      console.log(this.viaje);
      this.viajeService.create(this.viaje).subscribe(_response=>this.router.navigate(['/viajes']));
    });
  }

}
