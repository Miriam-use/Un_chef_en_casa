import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from "../services/auth.service";
import { GooglePlus } from '@ionic-native/google-plus/ngx';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  email: string;
  password: string;

  constructor(
    private authService: AuthService, 
    public router: Router,
    private googlePlus: GooglePlus) { }

  ngOnInit() {
    
  }

  onSubmitLogin()
  {
    this.authService.login(this.email, this.password).then( res =>{
      this.router.navigate(['/recetas']);
    }).catch(err => alert('los datos son incorrectos o no existe el usuario'))
  }

  //funcion para inicia sesion en google
  IniciarGoogle(){

  }
}
