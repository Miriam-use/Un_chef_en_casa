import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase } from '@angular/fire/database';
import { AuthService,  } from "../services/auth.service";
import { Router } from '@angular/router';
import { AngularFireStorageModule } from "@angular/fire/storage";

@Component({
  selector: 'app-dato',
  templateUrl: './dato.page.html',
  styleUrls: ['./dato.page.scss'],
})
export class DatoPage implements OnInit {

  idSelected:any; //Esta variable se cargará cuando elijamos un alumno, así controlamos si es un alumno nueva o para actualizar
  show:boolean; //Esta variable contralará cuando queremos que se muestren los campos para introducir o actualizar un alumno
  recetas = []; //Array donde cargaremos los alumnos que hay en la base de datos y las mostraremos en nuestra page
  receta = {id: null, nombre:null, tiempo:null, categoria:null, ingrediente:null, pasos:null, descripcion:null, foto:null}; //Declaramos un objeto vacio de alumno

  constructor(private router : Router, 
    private db: AngularFireDatabase, 
    public authService : AuthService,
    private storage:AngularFireStorageModule) { 
    this.show = false; //Inicializamos la variable a false, para que por defecto no se muestren los campos
    this.idSelected = null; // Inicializamos a null idselected, que significará que no tenemos ningun alumno existente selecionada.

    authService.getReceta()
    .subscribe(recetas => {
      this.recetas = recetas;
    });//Hacemos una llamada a nuestro servicio, al metodo getHeroes y nos devolvera toda los alumnos que hay en nuestra abase de datos
    // y las cargaremos en nuestro array
  }

  ngOnInit() {
  }

  saveRecetas(){
    if(this.idSelected != null){//si es diferente a null actualizamos, sino creamos uno nuevo
      this.authService.updateRecetas(this.receta);
    }else{
      this.authService.saveRecetas(this.receta);
    }

    this.clear();
  
    this.router.navigate(['/recetas']);
  }

  selecRecetas(id : string){ //selecionamos el alumno y mostramos los campos
    this.show = true;

    this.idSelected = id; //cogemos su id

    let receivedReceta : any; //declaramos un objeto vacio que será el que reciba la información de el alumno que seleccionamos

    this.authService.getRecetas(id) //hacemos uso de la funcion getHeroe de nuestro servicio
    .subscribe(receta => {
      receivedReceta = this.receta; //el objeto vacio recibe la variable
      this.receta = receivedReceta; //Y se lo asignamos al objeto heroe los valores que se han retornado del servicio
    });
  }

  removeSelectedRecetas(){
    //Llamamos a la funcion removeHeroe de nuestro servicio, le pasamos el idselected y se borra ese alumno
    this.authService.removeRecetas(this.idSelected);
    this.clear();
  }

  clear(){
    //inicializamos los valores de las variables una vez hecha una acción
    this.show = false;
    this.idSelected = null;
    this.receta.id = null;
    this.receta.nombre = null;
    this.receta.tiempo = null;
    this.receta.categoria = null;
    this.receta.ingrediente = null;
    this.receta.pasos = null;
    this.receta.descripcion = null;
    this.receta.foto = null;
  }

  uploadImage(e){
    console.log('subir', e);
    const id = Math.random().toString(36).substring(2);
    const file = e.target.files[0];
    const filePath = `upload/${id}`;
    const ref = this.storage.ref(filePath);
    const task = this.storage.upload(filePath, file);
  }

}
