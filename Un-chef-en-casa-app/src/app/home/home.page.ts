import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { AuthService,  } from "../services/auth.service";
import { Router } from '@angular/router';
import { AngularFireDatabase } from '@angular/fire/database';


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit{

  constructor() {
  }

  ngOnInit() {}
}
