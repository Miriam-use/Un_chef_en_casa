import { Injectable } from '@angular/core';
import { AngularFireAuth } from "@angular/fire/auth";
import { Router } from "@angular/router";
import { AngularFirestore } from "@angular/fire/firestore";
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from '@angular/fire/database';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private AFauth : AngularFireAuth, private router : Router, private db : AngularFirestore, public afDB: AngularFireDatabase) { }

  async login(email:string, password:string){

    return new Promise((resolve, rejected) =>{
      this.AFauth.signInWithEmailAndPassword(email, password).then(user => {
        resolve(user);
      }).catch(err => rejected(err));
    });

    //var result = await this.AFauth.auth.
  }

  logout(){
    this.AFauth.signOut().then(() => {
      this.router.navigate(['/login']);
    })
  }

  register(email : string, password : string, name : string){

    return new Promise ((resolve, reject) => {
      this.AFauth.createUserWithEmailAndPassword(email, password).then( res =>{
          // console.log(res.user.uid);
        const uid = res.user.uid;
          this.db.collection('users').doc(uid).set({
            name : name,
            uid : uid
          })
        
        resolve(res)
      }).catch( err => reject(err))
    })
  }

 public getReceta(){
   return this.afDB.list('recetas/').valueChanges();
   //Esta función devolverá todos los datos que tengamos en el apartado heroes, en nuestra base de datos
 }

 public saveRecetas(receta){
  let key = this.afDB.list('/recetas/').push(receta).key;
  //Guardamos los alumnos y obetenemos el id que firebase pone al nudulo de nuestros alumnos.
  //Al guardarse sin id nuestros alumnos, ahora la actualizamos con el id que firebase nos devuelve.
  receta.id = key;
  this.afDB.database.ref('recetas/'+receta.id).set(receta);
 }

 public updateRecetas(receta){
  //Actualizamos el alumno con el id que recibimos del objeto del parametro
  this.afDB.database.ref('recetas/'+receta.id).set(receta);
}
public getRecetas(id: string){
  return this.afDB.object('recetas/'+id).valueChanges();
  //Devolvera el alumno con el id que le pasamos por parametro
}
public removeRecetas(id: string){
  this.afDB.database.ref('recetas/'+id).remove();
  //Borrará el alumno con el id que le pasamos por parametro
}
}
