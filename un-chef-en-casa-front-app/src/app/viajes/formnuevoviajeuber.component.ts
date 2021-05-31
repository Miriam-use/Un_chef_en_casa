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

  constructor(private viajeService:ViajeService, private router:Router) { }

  ngOnInit(){
   
  }

}
