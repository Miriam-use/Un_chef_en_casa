import { Injectable } from '@angular/core';
import { AngularFireAuth } from "@angular/fire/auth";
import { Router } from "@angular/router";
import { AngularFirestore } from "@angular/fire/firestore";
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from '@angular/fire/database';
import { TaskI } from '../models/task.interface';

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
        const uid = res.user.uid;
          this.db.collection('users').doc(uid).set({
            name : name,
            uid : uid
          })
        
        resolve(res)
      }).catch( err => reject(err))
    })
  }

 getReceta(){
   return this.afDB.list('recetas/').valueChanges();
 }

 public saveRecetas(receta){
  let key = this.afDB.list('/recetas/').push(receta).key;
  receta.id = key;
  this.afDB.database.ref('recetas/'+receta.id).set(receta);
 }

 public updateRecetas(receta){
  this.afDB.database.ref('recetas/'+receta.id).set(receta);
}
public getRecetas(id: string){
  return this.afDB.object('recetas/'+id).valueChanges();
}
public removeRecetas(id: string){
  this.afDB.database.ref('recetas/'+id).remove();
}

//crud

create(todo: TaskI) {
  return this.db.collection('receta').add(todo);
}

getTasks() {
  return this.db.collection('receta').snapshotChanges();
}

getTask(id) {
  return this.db.collection('receta').doc(id).valueChanges();
}

update(id, todo: TaskI) {
  this.db.collection('receta').doc(id).update(todo)
    .then(() => {
      this.router.navigate(['/recetas']);
    }).catch(error => console.log(error));;
}

delete(id: string) {
  this.db.doc('receta/' + id).delete();
}

getUseAuth(){
  return this.AFauth.authState
}

}
