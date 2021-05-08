import { Component, OnInit } from '@angular/core';
import { AuthService,  } from "../services/auth.service";

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
})
export class PerfilPage implements OnInit {
  
  name: string;
  photo: string;
  email: string;

  constructor(public authService : AuthService) { }

  ngOnInit() {
    this.authService.getUseAuth().subscribe(user => {
      this.name = user.displayName;
      this.email = user.email;
      this.photo = user.photoURL;
    })
  }

}
