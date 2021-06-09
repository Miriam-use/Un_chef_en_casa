import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { HttpClientModule } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es';
import { GooglePlaceModule } from "ngx-google-places-autocomplete";

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { FormComponent } from './usuarios/form.component';
import { LoginComponent } from './usuarios/login.component';
import { UsuariodetalleComponent } from './usuarios/usuariodetalle.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { PaginatorComponent } from './paginator/paginator.component'

import { UsuarioService } from './usuarios/usuario.service';
import { DashboardService } from './dashboard/dashboard.service';
//import { ContractsService } from './contracts/contracts.service';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { RecetadetalleComponent } from './recetas/recetadetalle/recetadetalle.component';
import { RecetalistComponent } from './recetas/recetalist/recetalist.component';
import { FavoritoComponent } from './recetas/favorito/favorito.component';
import { RecetasComponent } from './recetas/recetas/recetas.component';
import { DetallesrecetasComponent } from './recetas/detallesrecetas/detallesrecetas.component';

registerLocaleData(localeES, 'es');

const routes: Routes=[
  {path:'',redirectTo:'/usuarios/login', pathMatch:'full'},
  {path:'home',component:HomeComponent},
  {path:'usuarios',component:UsuariosComponent},
  {path:'usuarios/pg/:pagina',component:UsuariosComponent},
  {path:'usuarios/form',component:FormComponent},
  {path:'usuarios/form/:id',component:FormComponent},
  {path:'usuarios/login',component:LoginComponent},
  {path:'usuarios/detalle',component:UsuariodetalleComponent},
  {path:'dashboard',component:DashboardComponent},
  {path:'recetadetalle',component:RecetadetalleComponent},
  {path:'recetadetalle/:id',component:RecetadetalleComponent},
  {path:'receta',component:RecetalistComponent},
  {path:'favorito',component:FavoritoComponent},
  {path:'receta/tabla/pg/:pagina',component:RecetasComponent},
  {path:'receta/detalles/:id',component:DetallesrecetasComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UsuariosComponent,
    FormComponent,
    PaginatorComponent,
    HomeComponent,
    LoginComponent,
    UsuariodetalleComponent,
    DashboardComponent,
    RecetadetalleComponent,
    RecetalistComponent,
    FavoritoComponent,
    RecetasComponent,
    DetallesrecetasComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatMomentDateModule,
    GooglePlaceModule,
    MatProgressBarModule 
  ],
  providers: [
    UsuarioService,
    FormComponent,
    DashboardService,
//    ContractsService,
    {provide: LOCALE_ID, useValue: 'es' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
