import { DoCheck } from '@angular/core';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Usuario } from '../usuarios/usuario';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements DoCheck {

  constructor(
    private router: Router,
  ) { }

  usuario: Usuario = new Usuario();
  logueado: boolean = false;

  ngDoCheck() {
    this.logueado = sessionStorage.getItem("usuariologueado") ? true : false;

    this.usuario=JSON.parse(sessionStorage.getItem("usuariologueado"));
  }

  logOut2() {
    sessionStorage.clear();
    this.router.navigate(['/usuarios/login'])
  }

  logOut(usuario: Usuario): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-3'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: 'Saliendo de la sesión...',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Abandonar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {

        sessionStorage.clear();
        this.logueado = false;
        this.router.navigate(['/usuarios/login']);
        swalWithBootstrapButtons.fire(
          'Log out',
          'Sessión Cerrada',
          'success'
        )
      } else if (result.dismiss === Swal.DismissReason.cancel) { }
    })
  }


}