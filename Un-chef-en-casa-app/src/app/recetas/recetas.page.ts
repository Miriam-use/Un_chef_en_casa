import { Component, OnInit } from '@angular/core';
import { AuthService,  } from "../services/auth.service";
import { Router } from '@angular/router';
import { AngularFireDatabase } from '@angular/fire/database';
import { CallNumber } from '@ionic-native/call-number/ngx';

@Component({
  selector: 'app-recetas',
  templateUrl: './recetas.page.html',
  styleUrls: ['./recetas.page.scss'],
})
export class RecetasPage implements OnInit {

  idSelected:any;
  show:boolean;
  recetas = [];
  receta = {id: null, nombre:null, tiempo:null, categoria:null, ingrediente:null, pasos:null, descripcion:null, foto:null};

  constructor(
    private router : Router, 
    public authService : AuthService, 
    private db: AngularFireDatabase) { 
      this.show = false;
    this.idSelected = null;

    authService.getReceta()
    .subscribe(recetas => {
      this.recetas = recetas;
    });
    }

  ngOnInit() {
  }

}
