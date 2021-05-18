import { Component, OnInit, ViewChild } from '@angular/core';
import { Proveedor } from './proveedor'
import { ProveedorService } from './proveedor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-proveedores',
  templateUrl: './proveedores.component.html',
  styleUrls: ['./proveedores.component.css']
})
export class ProveedoresComponent implements OnInit {

  proveedores: Proveedor[];

  constructor(private proveedorService:ProveedorService, private router:Router) { }

  ngOnInit(): void {
    this.cargarProveedores();
  }

  cargarProveedores():void{
    this.proveedorService.getProveedores().subscribe(proveedores=>this.proveedores=proveedores);
  }
}
